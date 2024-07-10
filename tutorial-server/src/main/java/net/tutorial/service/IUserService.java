package net.tutorial.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.tutorial.domain.User;
import net.tutorial.domain.dto.LoginDTO;
import net.tutorial.domain.dto.RegisterDTO;
import net.tutorial.domain.dto.UserInfoDTO;
import net.tutorial.domain.vo.UserInfoVO;
import net.tutorial.response.AjaxResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author top
 * @since 2024-06-17
 */
public interface IUserService extends IService<User> {
    /**
     * 查询用户
     *
     * @param id 用户主键
     * @return 用户
     */
    public User selectUserById(Long id);

    /**
     * 查询用户列表
     *
     * @param user 用户
     * @return 用户集合
     */
    public List<User> selectUserList(User user);

    /**
     * 新增用户
     *
     * @param user 用户
     * @return 结果
     */
    public int insertUser(User user);

    /**
     * 修改用户
     *
     * @param user 用户
     * @return 结果
     */
    public int updateUser(User user);

    /**
     * 批量删除用户
     *
     * @param ids 需要删除的用户主键集合
     * @return 结果
     */
    public int deleteUserByIds(Long[] ids);

    /**
     * 删除用户信息
     *
     * @param id 用户主键
     * @return 结果
     */
    public int deleteUserById(Long id);
    String updateUserInfo(Long userId, UserInfoDTO userInfoDTO);

    String updatePassword(String emailCode, LoginDTO loginDTO);

    String updateEmail(String email, String emailCode);

    UserInfoVO getUserInfo();
}
