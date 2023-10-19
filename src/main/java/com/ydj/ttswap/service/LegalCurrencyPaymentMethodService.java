package com.ydj.ttswap.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.entity.LegalCurrencyPaymentMethodEntity;

import java.util.Map;

/**
 * 法币支付方式映射表
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
public interface LegalCurrencyPaymentMethodService extends IService<LegalCurrencyPaymentMethodEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

