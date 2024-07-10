package net.tutorial.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 积分日志表VO
 * </p>
 *
 * @author top
 * @since 2024-07-05
 */
@Data
public class PointsLogVO implements Serializable {



    /**
     * 点数
     */
    private Integer point;

    /**
     * 原因
     */
    private String reason;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
