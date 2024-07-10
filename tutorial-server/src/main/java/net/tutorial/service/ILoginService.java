package net.tutorial.service;

import net.tutorial.domain.dto.RegisterDTO;
import net.tutorial.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import net.tutorial.response.AjaxResult;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author top
 * @since 2024-06-17
 */
public interface ILoginService extends IService<User> {
    //登录
    String login(String email, String password, String code, String uuid);
    //注册
    String register(RegisterDTO registerDTO);
    //发送验证码
    String sendEmail(String type, String uuid, String emailAddress, String captchaCode, HttpServletRequest request);
}
