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
 * 辅导班表
 * </p>
 *
 * @author top
 * @since 2024-07-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_tutor")
public class Tutor implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 辅导班名
     */
    private String name;

    /**
     * 封面
     */
    private String cover;

    /**
     * 详情
     */
    private String description;

    /**
     * 价钱
     */
    private Integer price;

    /**
     * 参加人数
     */
    private Integer attendNum;


}
