package net.tutorial.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 图书表
 * </p>
 *
 * @author top
 * @since 2024-06-10
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BooksVO implements Serializable {
    /**
     * ID
     */
    private Long id;

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
     * 浏览量
     */
    private Integer views;

    /**
     * 教程1 or 宝典2 or项目3
     */
    private String booksType;


}
