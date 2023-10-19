package com.ydj.ttswap.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ydj.ttswap.entity.PaymentAccountEntity;
import com.ydj.ttswap.entity.PaymentMethodEntity;
import com.ydj.ttswap.vo.PaymentAccountVo;

import java.util.List;
import java.util.Map;

/**
 * 收款账户配置信息表
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
public interface PaymentAccountService extends IService<PaymentAccountEntity> {

    List<PaymentAccountVo> queryPage(String js,Map<String, Object> params);

    Boolean saveAccount(String js,PaymentAccountEntity paymentAccount);

    List<PaymentAccountEntity> listSel(String js, Map<String,Object> params);

    List<PaymentMethodEntity> paySel(String js, Map<String,Object> params);
}

