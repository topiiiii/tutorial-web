package net.tutorial.domain.dto;

import lombok.Data;

/**
 * 用户登录对象
 *
 * @author top
 */
@Data
public class LoginDTO
{
    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 验证码
     */
    private String code;

    /**
     * 唯一标识
     */
    private String uuid;

}
