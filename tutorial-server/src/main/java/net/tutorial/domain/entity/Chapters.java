package net.tutorial.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 章节表
 * </p>
 *
 * @author top
 * @since 2024-06-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_chapters")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Chapters implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父章节id
     */
    private Long parentId;

    /**
     * 章节名
     */
    private String name;

    /**
     * 图书id
     */
    private Long bookId;

    /**
     * 顺序
     */
    private Integer sequence;

    /**
     * 解锁积分数量
     */
    private Integer unlockPoints;

    /**
     * 点赞量
     */
    private Integer likeNum;

    /**
     * 收藏量
     */
    private Integer collectionNum;



}
