package net.tutorial.domain.vo;

import lombok.Data;
import net.tutorial.domain.entity.Chapters;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 视频表
 * </p>
 *
 * @author top
 * @since 2024-06-16
 */
@Data
public class VideoVO implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 章节id
     */
    private Long chapterId;

    /**
     * 章节名
     */
    private String name;

    /**
     * 视频地址
     */
    private String video;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    //简介
    private String description;

    //章节目录
    private List<Chapters> chaptersList;
}
