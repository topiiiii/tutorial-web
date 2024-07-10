package net.tutorial.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 评论表
 * </p>
 *
 * @author top
 * @since 2024-06-20
 */
@Data
public class CommentVO implements Serializable {


    /**
     * ID
     */
    private Long id;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论用户的id
     */
    private Long fromUid;

    /**
     * 评论用户的头像
     */
    private String fromAvatar;

    /**
     * 评论用户的名称
     */
    private String fromName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 回复列表
     */
    private List<ReplyVO> replyVOList;
}
