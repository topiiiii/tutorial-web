package net.tutorial.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 会员类型表
 * </p>
 *
 * @author top
 * @since 2024-06-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_vip_type")
public class VipType implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int PERMANENT_VIP=-1;
    public static final int NO_VIP=-2;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 会员类型名字
     */
    private String name;

    /**
     * 权益描述
     */
    private String description;

    /**
     * 赠送积分数量
     */
    private Integer pointNum;

    /**
     * 会员费用单位分
     */
    private Integer fee;

    /**
     * 会员时长（天），永久会员-1
     */
    private Integer durationDays;


}
