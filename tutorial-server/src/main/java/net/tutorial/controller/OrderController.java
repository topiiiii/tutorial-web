package net.tutorial.controller;


import lombok.RequiredArgsConstructor;
import net.tutorial.domain.dto.OrderFormDTO;
import net.tutorial.domain.dto.OrdersPaymentDTO;
import net.tutorial.response.AjaxResult;
import net.tutorial.service.IOrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import static net.tutorial.response.AjaxResult.success;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author top
 * @since 2024-07-05
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/front/order")
public class OrderController {

    private final IOrderService orderService;

    /**
     * 创建订单
     * @param orderFormDTO
     * @return
     */
    @PostMapping("/create")
    public AjaxResult createOrder(@RequestBody OrderFormDTO orderFormDTO){
        return success(orderService.createOrder(orderFormDTO));
    }

    /**
     * 支付订单
     * TODO:假支付
     * @param ordersPaymentDTO
     * @return
     */
    @PostMapping("/pay")
    public AjaxResult payOrder(@RequestBody OrdersPaymentDTO ordersPaymentDTO){
        return success(orderService.payOrder(ordersPaymentDTO));
    }
}
