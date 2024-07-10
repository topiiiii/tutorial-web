package net.tutorial.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 回复表VO
 * </p>
 *
 * @author top
 * @since 2024-06-21
 */
@Data
public class ReplyVO implements Serializable {


    /**
     * ID
     */
    private Long id;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 回复评论id
     */
    private Long commentId;

    /**
     * 0对评论的回复or1对回复的回复
     */
    private String flag;

    /**
     * 回复者
     */
    private Long fromUid;

    /**
     * 被回复者
     */
    private Long toUid;

    /**
     * 回复者的头像
     */
    private String fromAvatar;

    /**
     * 被回复者的头像
     */
    private String toAvatar;

    /**
     * 回复者名称
     */
    private String fromName;

    /**
     * 被回复者名称
     */
    private String toName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
