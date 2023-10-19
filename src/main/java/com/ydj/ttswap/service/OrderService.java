package com.ydj.ttswap.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.entity.OrderEntity;

import java.util.List;
import java.util.Map;

/**
 * 订单信息表
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPages(String js, Map<String, Object> params);

    List<OrderEntity> lists();

    void cancel(OrderEntity i, String yj);

    List<OrderEntity> statusList();

    void statusMonitoring(OrderEntity i);
}

