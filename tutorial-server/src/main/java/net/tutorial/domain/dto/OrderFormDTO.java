package net.tutorial.domain.dto;

import lombok.Data;


/**
 * 订单提交表单
 *
 * @author top
 * @date 2024/7/5 16:31
 */
@Data
public class OrderFormDTO {
    /**
     * 订单类型（会员充值类型0，积分充值1，2辅导班）
     */
    private Integer type;

    //商品id
    private Long goodsId;

    /**
     * 购买数量
     */
    private Integer num;

    /**
     * 价格，分
     */
    private Integer price;
}
