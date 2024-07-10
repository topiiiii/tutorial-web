package net.tutorial.service.impl;

import com.baomidou.mybatisplus.extension.toolkit.Db;
import net.tutorial.domain.User;
import net.tutorial.domain.dto.OrderFormDTO;
import net.tutorial.domain.dto.OrdersPaymentDTO;
import net.tutorial.domain.entity.*;
import net.tutorial.domain.vo.OrderSubmitVO;
import net.tutorial.exception.ServiceException;
import net.tutorial.mapper.OrderMapper;
import net.tutorial.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.tutorial.utils.SecurityUtils;
import net.tutorial.utils.bean.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author top
 * @since 2024-07-05
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    /**
     * 创建订单
     *
     * @param orderFormDTO
     * @return
     */
    @Transactional
    public OrderSubmitVO createOrder(OrderFormDTO orderFormDTO) {
        //1.查询产品是否存在
        //1.1 会员类型
        User user = SecurityUtils.getLoginUserByApp().getUser();
        if (orderFormDTO.getType().equals(Order.TYPE_VIP)) {
            VipType vipType = Db.getById(orderFormDTO.getGoodsId(), VipType.class);
            if (vipType == null || !orderFormDTO.getPrice().equals(vipType.getFee())) {
                throw new ServiceException("产品不存在");
            }
            VipUser vipUser = Db.lambdaQuery(VipUser.class).eq(VipUser::getUserId, user.getId()).one();
            if (vipType.getDurationDays() == VipType.PERMANENT_VIP && vipUser.getTypeId().equals(vipType.getId())) {
                throw new ServiceException("你已经是永久会员");
            }
        }
        //1.2 辅导班
        if (orderFormDTO.getType().equals(Order.TYPE_TUTOR)) {
            Tutor tutor = Db.getById(orderFormDTO.getGoodsId(), Tutor.class);
            if (tutor == null || !orderFormDTO.getPrice().equals(tutor.getPrice())) {
                throw new ServiceException("产品不存在");
            }
        }
        //2.插入订单数据
        //2.1获取当前用户
        Order order = BeanUtils.copyBean(orderFormDTO, Order.class);
        order.setNumber(String.valueOf(System.currentTimeMillis()));
        order.setOrderTime(LocalDateTime.now());
        order.setAmount(order.getPrice() * order.getNum());
        order.setStatus(Order.UN_PAID);
        order.setUserId(user.getId());
        //2.2保存
        save(order);


        //4.封装VO返回
        return OrderSubmitVO.builder()
                .id(order.getId())
                .number(order.getNumber())
                .orderTime(order.getOrderTime())
                .amount(order.getAmount())
                .build();
    }

    /**
     * 支付
     *
     * @param ordersPaymentDTO
     * @return
     */
    @Override
    @Transactional
    public String payOrder(OrdersPaymentDTO ordersPaymentDTO) {
        User user = SecurityUtils.getLoginUserByApp().getUser();
        //0.首先查询支付单
        Order oldOrder = getById(ordersPaymentDTO.getId());
        //0.1判断是否存在支付时间
        if (oldOrder.getCheckoutTime()!=null) {
            // 已经支付成功，抛出异常
            throw new ServiceException("订单已经支付！");
        }

        //TODO:1.支付

        //2.支付完成，填写信息
        Order order = getById(ordersPaymentDTO.getId());
        order.setCheckoutTime(LocalDateTime.now());
        order.setPayMethod(ordersPaymentDTO.getPayMethod());
        updateById(order);

        //3.支付完成，加上用户的权益
        //3.1如果是积分充值，加上用户的积分
        if (order.getType().equals(Order.TYPE_POINT)) {
            user.setPointsNum(user.getPointsNum() + order.getAmount());
            //3.1记录积分日志
            PointsLog pointsLog = PointsLog.builder()
                    .userId(user.getId())
                    .point(order.getAmount())
                    .reason("充值")
                    .createTime(LocalDateTime.now())
                    .build();
            Db.save(pointsLog);
        }
        //3.2如果是会员充值，则给用户添加上会员时长
        else if (order.getType().equals(Order.TYPE_VIP)) {
            VipType vipType = Db.getById(order.getGoodsId(), VipType.class);
            VipUser vipUser = Db.lambdaQuery(VipUser.class).eq(VipUser::getUserId, user.getId()).one();

            if (vipType.getDurationDays() != VipType.PERMANENT_VIP) {
                vipUser.setTypeId(order.getGoodsId());
                // 计算两个日期之间的天数差
                long daysBetween = ChronoUnit.DAYS.between(vipUser.getStartDate(), vipUser.getEndDate());
                long totalDays = daysBetween + vipType.getDurationDays();
                //加入之前的天数与30天
                vipUser.setEndDate(LocalDate.now().plusDays(totalDays));
                vipUser.setStartDate(LocalDate.now());
                Db.updateById(vipUser);
            } else {
                vipUser.setTypeId(order.getGoodsId());
                //加入100年
                vipUser.setEndDate(LocalDate.now().plusYears(100));
                vipUser.setStartDate(LocalDate.now());
                Db.updateById(vipUser);
            }
        }

        //TODO:3.2如果是辅导班
        else if (order.getType().equals(Order.TYPE_TUTOR)) {

        }


        return "支付成功";
    }
}
