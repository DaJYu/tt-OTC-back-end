package com.ydj.ttswap.controller;

import com.ydj.ttswap.constant.OrderConstant;
import com.ydj.ttswap.entity.CommodityEntity;
import com.ydj.ttswap.entity.OrderEntity;
import com.ydj.ttswap.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@EnableScheduling
@EnableAsync
public class ScheduledTasks {

    /*定时任务*/
    @Autowired
    private OrderService orderService;
    @Autowired
    private CommodityService commodityService;

    //下单时间限制任务
    @Async
    @Scheduled(initialDelay =OrderConstant.DELAYED, fixedDelay = OrderConstant.ORDER_MONITORING)
    public void orderMonitoring() {
        List<OrderEntity> oreder = orderService.lists();
        Date date = new Date();
        oreder.stream().filter(i->i.getZdsx().getTime() <= date.getTime()).forEach(i ->{
            orderService.cancel(i,"订单超时，系统已自动取消！");
        });
    }
    //订单状态变更时间限制任务
    @Async
    @Scheduled(initialDelay =OrderConstant.DELAYED, fixedDelay = OrderConstant.STATUS_MONITORING)
    public void statusMonitoring() {
        List<OrderEntity> oreder = orderService.statusList();
        Date date = new Date();
        oreder.stream().filter(i->i.getZdsx().getTime() <= date.getTime()).forEach(i ->{
            orderService.statusMonitoring(i);
        });
    }
    //商品截止时间限制任务
    @Async
    @Scheduled(initialDelay =OrderConstant.DELAYED, fixedDelay = OrderConstant.STATUS_MONITORING)
    public void productDeadline() {
        List<CommodityEntity> commodity = commodityService.statusList();
        Date date = new Date();
        commodity.stream().filter(i->i.getJzsj().getTime() <= date.getTime()).forEach(i ->{
            commodityService.productDeadline(i);
        });
    }
}
