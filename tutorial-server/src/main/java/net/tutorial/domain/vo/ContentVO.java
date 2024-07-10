package net.tutorial.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.tutorial.domain.entity.Chapters;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 内容表VO
 * </p>
 *
 * @author top
 * @since 2024-06-12
 */
@Data
public class ContentVO implements Serializable {


    /**
     * ID
     */
    private Long id;

    /**
     * 内容
     */
    private String content;

    /**
     * 关联视频id
     */
    private Long videoId;


    /**
     * 章节id
     */
    private Long chapterId;

    /**
     * 章节名
     */
    private String name;

    //书名
    private String bookName;

    //上一节id
    private Long lastId;

    //下一节id
    private Long nextId;

    //章节目录
    private List<Chapters> chaptersList;
}
