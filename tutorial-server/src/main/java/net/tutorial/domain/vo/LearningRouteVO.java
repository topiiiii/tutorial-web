package net.tutorial.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 学习路线VO
 * </p>
 *
 * @author top
 * @since 2024-06-10
 */
@Data
public class LearningRouteVO implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 路线名字
     */
    private String name;

    /**
     * 封面
     */
    private String cover;

    private List<BooksVO> booksVOList;

}
