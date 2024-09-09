package com.sky.task;

import com.sky.entity.Orders;
import com.sky.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class Ordertask {
    @Autowired
    private OrderService orderService;

    @Scheduled(cron = "0 * * * * ? ")
    public void processTimeOutOrder(){
        log.info("定时任务执行中...");
        LocalDateTime now = LocalDateTime.now().plusMinutes(-15);
        List<Orders> orders = orderService.processTimeOut(Orders.DELIVERY_IN_PROGRESS,now);
        if (orders!=null&&orders.size() > 0){
            for (Orders order : orders){
                order.setStatus(Orders.CANCELLED);
                order.setCancelReason("订单超时取消");
                order.setCancelTime(LocalDateTime.now());
                orderService.updateStatus(order);
            }
        }
    }

    @Scheduled(cron = "0 0 1 * * ? ")
    public void processDeliveryOrder(){
        log.info("定时任务执行中...");
        LocalDateTime now = LocalDateTime.now().plusHours(-1);
        List<Orders> orders = orderService.processTimeOut(Orders.DELIVERY_IN_PROGRESS,now);
        if (orders!=null&&orders.size() > 0){
            for (Orders order : orders){
                order.setStatus(Orders.COMPLETED);
                orderService.updateStatus(order);
            }
        }
    }
}
