package net.tutorial.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 评论表DTO
 * </p>
 *
 * @author top
 * @since 2024-06-20
 */
@Data
public class CommentDTO implements Serializable {



    /**
     * 文章id
     */
    private Long articleId;

    /**
     * 类型（0面试经验or 1视频）
     */
    private String commentType;

    /**
     * 评论内容
     */
    private String content;



}
