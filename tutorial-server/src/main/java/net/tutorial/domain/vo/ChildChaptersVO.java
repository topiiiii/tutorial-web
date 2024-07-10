package net.tutorial.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 孩子章节VO
 * </p>
 *
 * @author top
 * @since 2024-06-12
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChildChaptersVO implements Serializable {


    /**
     * ID
     */
    private Long id;

    /**
     * 章节名
     */
    private String name;

    /**
     * 顺序
     */
    private Integer sequence;


//    /**
//     * 是否高级内容
//     */
//    private Boolean isPremium;

    /**
     * 解锁积分数量
     */
    private Integer unlockPoints;

    private List<ChildChaptersVO> childChaptersVOList;


}
