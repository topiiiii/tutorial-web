package net.tutorial.service;

import net.tutorial.domain.dto.OrderFormDTO;
import net.tutorial.domain.dto.OrdersPaymentDTO;
import net.tutorial.domain.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import net.tutorial.domain.vo.OrderSubmitVO;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author top
 * @since 2024-07-05
 */
public interface IOrderService extends IService<Order> {

    OrderSubmitVO createOrder(OrderFormDTO orderFormDTO);

    String payOrder(OrdersPaymentDTO ordersPaymentDTO);
}
