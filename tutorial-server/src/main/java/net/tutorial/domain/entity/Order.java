package net.tutorial.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author top
 * @since 2024-07-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    //订单类型
    public static final Integer TYPE_VIP=0;
    public static final Integer TYPE_POINT=1;
    public static final Integer TYPE_TUTOR=2;

    //订单状态
    public static final Integer UN_PAID=1;
    public static final Integer PAID=2;
    public static final Integer REFUND=3;


    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //商品id
    private Long goodsId;
    /**
     * 订单类型（0会员充值类型，积分充值1，2辅导班）
     */
    private Integer type;

    /**
     * 订单号
     */
    private String number;

    /**
     * 订单状态（1待付款 2已完成 3已取消）
     */
    private Integer status;

    /**
     * 支付方式(1微信,2支付宝)
     */
    private Integer payMethod;

    /**
     * 下单用户
     */
    private Long userId;

    /**
     * 下单时间
     */
    private LocalDateTime orderTime;

    /**
     * 结账时间
     */
    private LocalDateTime checkoutTime;

    /**
     * 实收金额(单位为分)
     */
    private Integer amount;

    /**
     * 订单取消原因
     */
    private String cancelReason;

    /**
     * 购买数量
     */
    private Integer num;

    /**
     * 价格，分
     */
    private Integer price;


}
