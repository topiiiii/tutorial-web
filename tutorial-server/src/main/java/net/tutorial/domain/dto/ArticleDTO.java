package net.tutorial.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 面试经验文章表DTO
 * </p>
 *
 * @author top
 * @since 2024-06-11
 */
@Data
public class ArticleDTO implements Serializable {

    /**
     * 分类id
     */
    private Long typeId;

    /**
     * 标题
     */
    private String title;

    /**
     * 封面
     */
    private String cover;

    /**
     * 内容
     */
    private String content;

    /**
     * 文章类型（1原创，2转载，3翻译)
     */
    private String articleType;

    /**
     * 状态（0表示已发布，1表示草稿，2表示删除）
     */
    private String state;


}
