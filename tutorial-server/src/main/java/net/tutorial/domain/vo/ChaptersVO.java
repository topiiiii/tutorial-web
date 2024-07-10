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
 * 章节VO
 * </p>
 *
 * @author top
 * @since 2024-06-12
 */
@Data
public class ChaptersVO implements Serializable {


    private Long bookId;

    /**
     * 书名
     */
    private String bookName;

    /**
     * 描述(章节前面的简介)
     */
    private String description;


    private List<ChildChaptersVO> chapters;


}
