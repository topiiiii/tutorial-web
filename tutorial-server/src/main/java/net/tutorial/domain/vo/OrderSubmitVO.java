package net.tutorial.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 订单提交VO
 *
 * @author top
 * @date 2024/7/5 18:17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderSubmitVO {

    /**
     * ID
     */
    private Long id;


    /**
     * 订单号
     */
    private String number;


    /**
     * 下单时间
     */
    private LocalDateTime orderTime;


    /**
     * 实收金额(单位为分)
     */
    private Integer amount;

}
