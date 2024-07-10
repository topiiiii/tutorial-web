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
public class ReplyDTO implements Serializable {

    /**
     * 回复评论id
     */
    private Long commentId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 0对评论的回复or1对回复的回复
     */
    private String flag;



}
