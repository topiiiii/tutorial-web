package net.tutorial.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 回复表
 * </p>
 *
 * @author top
 * @since 2024-06-21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_reply")
public class Reply implements Serializable {

    public static final String  TO_COMMENTS ="0";
    public static final String  TO_REPLY ="1";

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 回复评论或者回复id
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

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
