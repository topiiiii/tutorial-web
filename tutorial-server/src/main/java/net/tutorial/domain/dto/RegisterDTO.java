package net.tutorial.domain.dto;

import lombok.Data;

/**
 * 类描述
 *
 * @author top
 * @date 2024/6/17 14:17
 */
@Data
public class RegisterDTO {

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 邮箱验证码
     */
    private String emailCode;

    /**
     * 验证码
     */
    private String code;

    /**
     * 唯一标识
     */
    private String uuid;
}
