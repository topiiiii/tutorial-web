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
 * 学习路线VO
 * </p>
 *
 * @author top
 * @since 2024-06-11
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TotalRouteVO implements Serializable {


    /**
     * ID
     */
    private Long id;

    /**
     * 类型id
     */
    private Long typeId;

    /**
     * 模块名
     */
    private String moduleName;

    /**
     * 技能获得
     */
    private String gain;

    //1.一个模块对应多个学习路线，一个技能
    //2.一个学习路线对应多个教程
    private List<LearningRouteVO> learningRouteVOList;
}
