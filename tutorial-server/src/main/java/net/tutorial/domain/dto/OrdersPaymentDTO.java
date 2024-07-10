package net.tutorial.domain.dto;

import lombok.Data;
import java.io.Serializable;

@Data
public class OrdersPaymentDTO implements Serializable {
    /**
     * 订单id
     */
    private Long id;


    /**
     * 支付方式(1微信,2支付宝)
     */
    private Integer payMethod;

}
