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
 * 学习路线总体表
 * </p>
 *
 * @author top
 * @since 2024-06-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_total_route")
public class TotalRoute implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 类型id
     */
    private Long typeId;

    /**
     * 模块名
     */
    private String moduleName;

    /**
     * 技能获得
     */
    private String gain;

    /**
     * 学习路线id,分隔
     */
    private String routeId;


}
