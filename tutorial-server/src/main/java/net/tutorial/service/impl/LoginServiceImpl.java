package net.tutorial.service.impl;

import com.baomidou.mybatisplus.extension.toolkit.Db;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.tutorial.constant.CacheConstants;
import net.tutorial.constant.Constants;
import net.tutorial.constant.UserConstants;
import net.tutorial.domain.LoginUserByApp;
import net.tutorial.domain.dto.RegisterDTO;
import net.tutorial.domain.User;
import net.tutorial.domain.entity.VipType;
import net.tutorial.domain.entity.VipUser;
import net.tutorial.exception.ServiceException;
import net.tutorial.exception.user.CaptchaException;
import net.tutorial.exception.user.CaptchaExpireException;
import net.tutorial.exception.user.UserNotExistsException;
import net.tutorial.exception.user.UserPasswordNotMatchException;
import net.tutorial.manager.AsyncManager;
import net.tutorial.manager.factory.AsyncFactory;
import net.tutorial.mapper.UserMapper;
import net.tutorial.service.ILoginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.tutorial.service.SysPasswordService;
import net.tutorial.service.SysPermissionService;
import net.tutorial.service.TokenService;
import net.tutorial.utils.*;
import net.tutorial.utils.enums.UserStatus;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
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
public class LoginServiceImpl extends ServiceImpl<UserMapper, User> implements ILoginService {

    private final RedisCache redisCache;

    private final UserMapper userMapper;

    private final TokenService tokenService;

    private final SysPermissionService permissionService;

    private final SysPasswordService passwordService;

    private final Random random;


    @Value("${tutorial.defaultAvatar}")
    private String defaultAvatar;

    /**
     * 登录
     * @param email
     * @param password
     * @param code
     * @param uuid
     * @return
     */
    @Override
    public String login(String email, String password, String code, String uuid) {
        // 1.验证码、信息校验
        verify( email,  password,  code,  uuid);
        //登录用户状态
        User user = lambdaQuery().eq(User::getEmail,email).one();
        if (StringUtils.isNull(user))
        {
            log.info("登录用户：{} 不存在.", email);
            throw new ServiceException(MessageUtils.message("user.not.exists"));
        }
        else if ((user.getStatus()) == Integer.valueOf(UserStatus.DELETED.getCode()).intValue())
        {
            log.info("登录用户：{} 已被冻结.", email);
            throw new ServiceException(MessageUtils.message("user.password.delete"));
        }
        //对比密码
        if (!passwordService.matches(user,password)){
            log.info("登录用户：{} 密码错误.", email);
            throw new ServiceException(MessageUtils.message("user.not.exists"));
        }
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(email, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        LoginUserByApp loginUserByApp = new LoginUserByApp(user.getId(),  110L, user, permissionService.getMenuPermission(user));
        // 生成token
        return tokenService.createToken(loginUserByApp);
    }

    /**
     * 注册
     * @param registerDTO
     * @return
     */
    @Override
    public String register(RegisterDTO registerDTO) {
        //1.检查用户是否已经注册
        User user =  lambdaQuery().eq(User::getEmail,registerDTO.getEmail()).one();;
        if (user != null) {
            throw new ServiceException("用户已经注册");
        }

        //2.检查验证码是否过期
        verify(registerDTO.getEmail(),registerDTO.getPassword(),registerDTO.getCode(),registerDTO.getUuid());
        //第四步：检查邮箱验证码是否正确
        String emailVerifyCode = (String) redisCache.getCacheObject(Constants.KEY_EMAIL_CODE_CONTENT + registerDTO.getEmail());
        if (StringUtils.isEmpty(emailVerifyCode)) {
            throw new ServiceException("邮箱验证码已过期");
        }
        if (!emailVerifyCode.equals(registerDTO.getEmailCode())) {
            throw new ServiceException("邮箱验证码不正确");
        } else {
            //正确，干掉redis里的内容
            redisCache.deleteObject(Constants.KEY_EMAIL_CODE_CONTENT + registerDTO.getEmail());
        }


        //3.注册
        User user1 = User.builder()
                .nickname(registerDTO.getNickname())
                .email(registerDTO.getEmail())
                .password(SecurityUtils.encryptPassword(registerDTO.getPassword()))
                .status(User.STATUS_NORMAL)
                .avatar(defaultAvatar)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        userMapper.insert(user1);
        //3.1添加普通会员信息
        VipType vipType = Db.lambdaQuery(VipType.class).eq(VipType::getDurationDays, VipType.NO_VIP).one();
        VipUser vipUser = VipUser.builder()
                .userId(user1.getId())
                .typeId(vipType.getId())
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build();
        Db.save(vipUser);

        //4.记录日志
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(registerDTO.getEmail(), Constants.REGISTER, MessageUtils.message("user.register.success")));
        LoginUserByApp loginUserByApp = new LoginUserByApp(user1.getId(),  110L, user1, permissionService.getMenuPermission(user1));
        // 生成token
        return tokenService.createToken(loginUserByApp);
    }

    /**
     * 发送邮箱验证码
     * 使用场景：注册、找回密码、修改邮箱（会输入新的邮箱）
     * 注册(register)：如果已经注册过了，就提示说，该邮箱已经注册
     * 找回密码(forget)：如果没有注册过，提示该邮箱没有注册
     * 修改邮箱(update)（新的邮箱）：如果已经注册了，提示改邮箱已经注册
     *
     * @param address
     * @param address
     * @return
     */
    @Override
    public String sendEmail(String type, String uuid, String address, String captchaCode,HttpServletRequest request) {
        //检查人类验证码是否正确
        // 验证码校验
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null)
        {
            throw new CaptchaExpireException();
        }
        if (!captchaCode.equalsIgnoreCase(captcha))
        {
            throw new CaptchaException();
        }
        if (address == null) {
            throw new ServiceException("邮箱地址不可以为空");
        }
        //根据类型，查询邮箱是否存在
        if ("register".equals(type) || "update".equals(type)) {
            User userByEmail =  lambdaQuery().eq(User::getEmail,address).one();;
            if (userByEmail != null) {
                throw new ServiceException("该邮箱已注册");
            }
        } else if ("forget".equals(type)) {
            User userByEmail =  lambdaQuery().eq(User::getEmail,address).one();;
            if (userByEmail == null) {
                throw new ServiceException("该邮箱未注册");
            }
        }
        //1、防止暴力发送，就是不断发地送：同一个邮箱，间隔要超过30秒发一次，同一个Ip，1小时内最多只能发10次（如果是短信，你最多只能发5次）
        String remoteAddr = request.getRemoteAddr();
        log.info("sendEmail == > ip == > " + remoteAddr);
        if (remoteAddr != null) {
            remoteAddr = remoteAddr.replaceAll(":", "_");
        }
        //拿出来，如果没有，那就过了
        log.info("Constants.User.KEY_EMAIL_SEND_IP + remoteAddr === > " + Constants.KEY_EMAIL_SEND_IP + remoteAddr);
        String ipSendTimeValue = (String) redisCache.getCacheObject(Constants.KEY_EMAIL_SEND_IP + remoteAddr);
        Integer ipSendTime;
        if (ipSendTimeValue != null) {
            ipSendTime = Integer.parseInt(ipSendTimeValue);
        } else {
            ipSendTime = 1;
        }
        if (ipSendTime > 10) {
            throw new ServiceException("您发送验证码也太频繁了吧！");
        }
        Object hasEmailSend = redisCache.getCacheObject(Constants.KEY_EMAIL_SEND_ADDRESS + address);
        if (hasEmailSend != null) {
            throw new ServiceException("您发送验证码也太频繁了吧！");
        }
        //2、检查邮箱地址是否正确
        boolean isEmailFormatOk = StringUtils.isEmailAddressOk(address);
        if (!isEmailFormatOk) {
            throw new ServiceException("邮箱地址格式不正确");
        }
        // 0~999999
        int code = random.nextInt(999999);
        if (code < 100000) {
            code += 100000;
        }
        log.info("sendEmail ==> code == > " + code);
        //3、发送验证码,6位数：100000~999999
        try {
            sendEmailVerifyCode(String.valueOf(code), address);
        } catch (Exception e) {
            throw new ServiceException("验证码发送失败，请稍后重试.");
        }
        //4、做记录
        //发送记录，code
        //
        if (ipSendTime == null) {
            ipSendTime = 0;
        }
        ipSendTime++;
        //1个小时有效期
        redisCache.setCacheObject(Constants.KEY_EMAIL_SEND_IP + remoteAddr, String.valueOf(ipSendTime), 60, TimeUnit.MINUTES);
        redisCache.setCacheObject(Constants.KEY_EMAIL_SEND_ADDRESS + address, "true", 1, TimeUnit.MINUTES);
        //保存code，10分钟内有效
        redisCache.setCacheObject(Constants.KEY_EMAIL_CODE_CONTENT + address, String.valueOf(code), Constants.CAPTCHA_EXPIRATION_EMAIL, TimeUnit.MINUTES);
        return "验证码发送成功";
    }
    /**
     * 校验信息
     * @param email
     * @param password
     * @param code
     * @param uuid
     */
    void verify(String email, String password,String code, String uuid){
        // 验证码校验
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null)
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(email, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(email, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
            throw new CaptchaException();
        }
        // 用户名或密码为空 错误
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(email, Constants.LOGIN_FAIL, MessageUtils.message("not.null")));
            throw new UserNotExistsException();
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(email, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            throw new UserPasswordNotMatchException();
        }
        // 检查邮箱格式 错误
        if (!StringUtils.isEmailAddressOk(email))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(email, Constants.LOGIN_FAIL, MessageUtils.message("user.email.not.valid")));
            throw new UserPasswordNotMatchException();
        }

    }

    /**
     * 异步发送邮件
     * @param verifyCode
     * @param emailAddress
     * @throws Exception
     */
    @Async
    public void sendEmailVerifyCode(String verifyCode, String emailAddress) throws Exception {
        EmailSender.sendRegisterVerifyCode(verifyCode, emailAddress);
    }


}
