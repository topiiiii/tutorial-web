package net.tutorial.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户信息VO
 * </p>
 *
 * @author top
 * @since 2024-06-17
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVO implements Serializable {


    /**
     * 用户ID
     */
    private Long id;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String avatar;


    /**
     * 积分数量
     */
    private Integer pointsNum;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 会员类型id
     */
    private Long typeId;

    /**
     * 会员类型名字
     */
    private String vipName;

    /**
     * 开通日期
     */
    private LocalDate startDate;

}
