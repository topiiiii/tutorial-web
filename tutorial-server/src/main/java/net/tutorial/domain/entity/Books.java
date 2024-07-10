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
 * 图书表
 * </p>
 *
 * @author top
 * @since 2024-06-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_books")
public class Books implements Serializable {

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
     * 书名
     */
    private String name;

    /**
     * 封面
     */
    private String cover;

    /**
     * 简介
     */
    private String briefly;

    /**
     * 描述(章节前面的简介)
     */
    private String description;

    /**
     * 浏览量
     */
    private Integer views;

    /**
     * 教程1 or 宝典2 or项目3
     */
    private String booksType;


}
