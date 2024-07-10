package net.tutorial.controller;

import lombok.RequiredArgsConstructor;
import net.tutorial.domain.dto.LoginDTO;
import net.tutorial.domain.dto.UserInfoDTO;
import net.tutorial.response.AjaxResult;
import net.tutorial.service.IUserService;
import org.springframework.web.bind.annotation.*;

import static net.tutorial.response.AjaxResult.success;

/**
 * 用户信息
 *
 * @author top
 * @date 2024/6/19 13:44
 */
@RestController
@RequestMapping("/front/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

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
    @PutMapping("/{userId}")
    public AjaxResult updateUserInfo(@PathVariable("userId") Long userId,
                                     @RequestBody UserInfoDTO userInfoDTO){
        return success(userService.updateUserInfo(userId,userInfoDTO));
    }


    /**
     * 修改密码password
     * 修改密码
     * 普通做法：通过旧密码对比来更新密码
     * <p>
     * 即可以找回密码，也可以修改密码
     * 发送验证码到邮箱/手机---> 判断验证码是否真确来判断
     * 对应邮箱/手机号码所注册的账号是否属于你。
     * <p>
     * 步骤：
     * 1、用户填写邮箱
     * 2、用户获取验证码type=forget
     * 3、填写验证码
     * 4、填写新的密码
     * 5、提交数据
     * <p>
     * 数据包括：
     * <p>
     * 1、邮箱和新密码
     * 2、验证码
     * <p>
     * 如果验证码正确-->所用邮箱注册的账号就是你的，可以修改密码
     *
     * @return
     */
    @PutMapping("/password/{email-code}")
    public AjaxResult updatePassword(@PathVariable("email-code") String emailCode,
                                     @RequestBody LoginDTO loginDTO){
        return success(userService.updatePassword(emailCode,loginDTO));
    }

    /**
     * 1、必须已经登录了
     * 2、新的邮箱没有注册过
     * <p>
     * 用户的步骤：
     * 1、已经登录
     * 2、输入新的邮箱地址
     * 3、获取验证码 type=update
     * 4、输入验证码
     * 5、提交数据
     * <p>
     * 需要提交的数据
     * 1、新的邮箱地址
     * 2、验证码
     * 3、其他信息我们可以token里获取
     *
     * @return
     */
    @PutMapping("/email")
    public AjaxResult updateEmail(@RequestParam("email") String email,
                                      @RequestParam("email-code") String emailCode) {
        return success(userService.updateEmail(email, emailCode));
    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping()
    public AjaxResult getUserInfo(){
        return success(userService.getUserInfo());
    }
}
