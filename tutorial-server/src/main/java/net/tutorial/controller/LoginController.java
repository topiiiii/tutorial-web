package net.tutorial.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.tutorial.constant.Constants;
import net.tutorial.domain.dto.LoginDTO;
import net.tutorial.domain.dto.RegisterDTO;
import net.tutorial.response.AjaxResult;
import net.tutorial.service.ILoginService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static net.tutorial.response.AjaxResult.success;

/**
 * <p>
 * 登录 前端控制器
 * </p>
 *
 * @author top
 * @since 2024-06-17
 */
@Slf4j
@RestController
@RequestMapping("/front")
@RequiredArgsConstructor
public class LoginController {

    private final ILoginService loginService;

    /**
     * 登录
     *
     * @param loginDTO 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginDTO loginDTO)
    {
        AjaxResult ajax = success();
        // 生成令牌
        String token = loginService.login(loginDTO.getEmail(), loginDTO.getPassword(), loginDTO.getCode(),
                loginDTO.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 注册
     * @param registerDTO
     * @return
     */
    @PostMapping("/register")
    public AjaxResult register(@RequestBody RegisterDTO registerDTO)
    {
        AjaxResult ajax = success();
        // 生成令牌
        String token = loginService.register(registerDTO);
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 发送邮件email
     * <p>
     * 使用场景：注册、找回密码、修改邮箱（会输入新的邮箱）
     * 注册：如果已经注册过了，就提示说，该邮箱已经注册
     * 找回密码：如果没有注册过，提示该邮箱没有注册
     * 修改邮箱（新的邮箱）：如果已经注册了，提示改邮箱已经注册
     * <p>
     * 注册(register)：如果已经注册过了，就提示说，该邮箱已经注册
     * 找回密码(forget)：如果没有注册过，提示该邮箱没有注册
     * 修改邮箱(update)（新的邮箱）：如果已经注册了，提示改邮箱已经注册
     *
     * @return
     */
    @GetMapping("/verify-code")
    public AjaxResult sendVerifyCode(HttpServletRequest request,
                                     @RequestParam("type") String type,
                                     @RequestParam("email") String emailAddress,
                                     @RequestParam("captchaCode") String captchaCode,
                                     @RequestParam("uuid") String uuid) {
        log.info("email == > " + emailAddress);
        return success(loginService.sendEmail(type, uuid, emailAddress, captchaCode,request));
    }


}
