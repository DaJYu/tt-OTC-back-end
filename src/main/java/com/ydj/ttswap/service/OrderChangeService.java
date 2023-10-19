package com.ydj.ttswap.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.entity.OrderChangeEntity;

import java.util.Map;

/**
 * 订单变更信息表
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
public interface OrderChangeService extends IService<OrderChangeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

