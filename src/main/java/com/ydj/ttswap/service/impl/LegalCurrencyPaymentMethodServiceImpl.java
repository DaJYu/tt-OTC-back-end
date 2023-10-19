package com.ydj.ttswap.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.utils.Query;

import com.ydj.ttswap.mapper.LegalCurrencyPaymentMethodMapper;
import com.ydj.ttswap.entity.LegalCurrencyPaymentMethodEntity;
import com.ydj.ttswap.service.LegalCurrencyPaymentMethodService;


@Service("legalCurrencyPaymentMethodService")
public class LegalCurrencyPaymentMethodServiceImpl extends ServiceImpl<LegalCurrencyPaymentMethodMapper, LegalCurrencyPaymentMethodEntity> implements LegalCurrencyPaymentMethodService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LegalCurrencyPaymentMethodEntity> page = this.page(
                new Query<LegalCurrencyPaymentMethodEntity>().getPage(params),
                new QueryWrapper<LegalCurrencyPaymentMethodEntity>()
        );

        return new PageUtils(page);
    }

}