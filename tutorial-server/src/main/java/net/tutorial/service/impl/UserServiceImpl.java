package net.tutorial.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.tutorial.constant.CacheConstants;
import net.tutorial.constant.Constants;
import net.tutorial.constant.UserConstants;
import net.tutorial.domain.LoginUserByApp;
import net.tutorial.domain.User;
import net.tutorial.domain.dto.LoginDTO;
import net.tutorial.domain.dto.RegisterDTO;
import net.tutorial.domain.dto.UserInfoDTO;
import net.tutorial.domain.entity.VipType;
import net.tutorial.domain.entity.VipUser;
import net.tutorial.domain.vo.UserInfoVO;
import net.tutorial.exception.ServiceException;
import net.tutorial.exception.user.CaptchaException;
import net.tutorial.exception.user.CaptchaExpireException;
import net.tutorial.exception.user.UserNotExistsException;
import net.tutorial.exception.user.UserPasswordNotMatchException;
import net.tutorial.manager.AsyncManager;
import net.tutorial.manager.factory.AsyncFactory;
import net.tutorial.mapper.UserMapper;
import net.tutorial.service.ILoginService;
import net.tutorial.service.IUserService;
import net.tutorial.service.SysPermissionService;
import net.tutorial.service.TokenService;
import net.tutorial.utils.*;
import org.apache.http.util.TextUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author top
 * @since 2024-06-17
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final RedisCache redisCache;

    private final TokenService tokenService;

    private final SysPermissionService permissionService;

    private final UserMapper userMapper;

    /**
     * 查询用户
     *
     * @param id 用户主键
     * @return 用户
     */
    @Override
    public User selectUserById(Long id)
    {
        return userMapper.selectUserById(id);
    }

    /**
     * 查询用户列表
     *
     * @param user 用户
     * @return 用户
     */
    @Override
    public List<User> selectUserList(User user)
    {
        return userMapper.selectUserList(user);
    }

    /**
     * 新增用户
     *
     * @param user 用户
     * @return 结果
     */
    @Override
    public int insertUser(User user)
    {
        user.setCreateTime(LocalDateTime.now());
        return userMapper.insertUser(user);
    }

    /**
     * 修改用户
     *
     * @param user 用户
     * @return 结果
     */
    @Override
    public int updateUser(User user)
    {
        user.setUpdateTime(LocalDateTime.now());
        return userMapper.updateUser(user);
    }

    /**
     * 批量删除用户
     *
     * @param ids 需要删除的用户主键
     * @return 结果
     */
    @Override
    public int deleteUserByIds(Long[] ids)
    {
        return userMapper.deleteUserByIds(ids);
    }

    /**
     * 删除用户信息
     *
     * @param id 用户主键
     * @return 结果
     */
    @Override
    public int deleteUserById(Long id)
    {
        return userMapper.deleteUserById(id);
    }


    /**
     * 用户修改个人信息
     * 允许用户修改的内容
     * 1、头像
     * 2、用户名
     * 3、密码 （单独修改）
     * 4、Email （唯一的，单独修改）
     * 5、手机号 （单独修改）暂定
     *
     * @param userId
     * @param userInfoDTO
     * @return
     */
    @Override
    public String updateUserInfo(Long userId, UserInfoDTO userInfoDTO) {
        //1.检查要修改的用户id与登录的用户是否同一个
        if (!userId.equals(SecurityUtils.getUserIdByApp())) {
            throw new ServiceException("请重新登录");
        }

        //2.查询用户
        User user = getById(userId);

        //3.判断穿过来的用户信息是否为null
        //3.1昵称
        String nickname = userInfoDTO.getNickname();
        if (!StringUtils.isEmpty(nickname) && !nickname.equals(user.getNickname())) {
            user.setNickname(nickname);
        }
        //3.2头像
        String avatar = userInfoDTO.getAvatar();
        if (!StringUtils.isEmpty(avatar) && !avatar.equals(user.getAvatar())) {
            user.setAvatar(avatar);
        }

        //4.更新
        user.setUpdateTime(LocalDateTime.now());
        updateById(user);

        //5.干掉redis里的信息存入新的
        LoginUserByApp loginUserByApp = SecurityUtils.getLoginUserByApp();
        loginUserByApp.setUser(user);
        tokenService.setLoginUser(loginUserByApp);

        return "用户信息更新成功";
    }

    /**
     *更新密码
     * @param emailCode
     * @param loginDTO
     * @return
     */
    @Override
    public String updatePassword(String emailCode, LoginDTO loginDTO) {
        //1.检查信息是否填写
        String email = loginDTO.getEmail();
        if (StringUtils.isEmpty(email)||StringUtils.isEmpty(loginDTO.getPassword())) {
            throw new ServiceException("不可以为空");
        }
        //1.1检查当前用户
        Long userIdByApp = SecurityUtils.getUserIdByApp();
        User user = getById(userIdByApp);
        if (user==null) {
            throw new ServiceException("请重新登录");
        }

        //2.对比邮箱验证码
        String emailVerifyCode = (String) redisCache.getCacheObject(Constants.KEY_EMAIL_CODE_CONTENT + email);
        if (StringUtils.isEmpty(emailVerifyCode)) {
            throw new ServiceException("邮箱验证码已过期");
        }
        if (!emailVerifyCode.equals(emailVerifyCode)) {
            throw new ServiceException("邮箱验证码不正确");
        } else {
            //正确，干掉redis里的内容
            redisCache.deleteObject(Constants.KEY_EMAIL_CODE_CONTENT + email);
        }

        //3.修改密码
        user.setPassword(SecurityUtils.encryptPassword(loginDTO.getPassword()));
        user.setUpdateTime(LocalDateTime.now());
        updateById(user);

        //4.干掉redis里的信息存入新的
        LoginUserByApp loginUserByApp = SecurityUtils.getLoginUserByApp();
        loginUserByApp.setUser(user);
        tokenService.setLoginUser(loginUserByApp);

        return "密码修改成功";
    }

    /**
     * 更新邮箱
     * @param email
     * @param emailCode
     * @return
     */
    @Override
    public String updateEmail(String email, String emailCode) {
        //1.检查当前用户是否登录
        User user = SecurityUtils.getLoginUserByApp().getUser();
        if (user==null) {
            throw new ServiceException("请重新登录");
        }

        //2.对比验证码，确保新的邮箱地址是属于当前用户的
        String redisVerifyCode = (String) redisCache.getCacheObject(Constants.KEY_EMAIL_CODE_CONTENT + email);
        if (StringUtils.isEmpty(redisVerifyCode) || !redisVerifyCode.equals(emailCode)) {
            throw new ServiceException("请重新登录");
        }
        //2.1正确，干掉redis里的内容
        redisCache.deleteObject(Constants.KEY_EMAIL_CODE_CONTENT + email);

        //3.更新邮箱
        user.setEmail(email);
        updateById(user);

        //4.干掉redis里的信息存入新的
        LoginUserByApp loginUserByApp = SecurityUtils.getLoginUserByApp();
        loginUserByApp.setUser(user);
        tokenService.setLoginUser(loginUserByApp);

        return "邮箱修改成功";
    }

    /**
     * 获取用户个人信息
     * @return
     */
    @Override
    public UserInfoVO getUserInfo() {
        //1.检查当前用户是否登录
        User user = SecurityUtils.getLoginUserByApp().getUser();
        if (user==null) {
            throw new ServiceException("请重新登录");
        }

        //查询用户会员等级
        VipUser vipUser = Db.lambdaQuery(VipUser.class).eq(VipUser::getUserId, user.getId()).one();
        VipType vipType = Db.getById(vipUser.getTypeId(), VipType.class);


        return  UserInfoVO.builder()
                .id(user.getId())
                .phone(user.getPhone())
                .nickname(user.getNickname())
                .pointsNum(user.getPointsNum())
                .createTime(user.getCreateTime())
                .avatar(user.getAvatar())
                .email(user.getEmail())
                .typeId(vipUser.getTypeId())
                .vipName(vipType.getName())
                .startDate(vipUser.getStartDate())
                .build();
    }
}
