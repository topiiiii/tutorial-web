package net.tutorial.domain.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * 用户修改个人信息
 *
 * @author top
 */
@Data
public class UserInfoDTO
{

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String avatar;

}
