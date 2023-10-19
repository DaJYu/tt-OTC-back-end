package com.ydj.ttswap.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.entity.PaymentMethodEntity;

import java.util.List;
import java.util.Map;

/**
 * 支付方式信息表
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
public interface PaymentMethodService extends IService<PaymentMethodEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<PaymentMethodEntity> selectList(String js);
}

