package com.ydj.ttswap.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.utils.Query;

import com.ydj.ttswap.mapper.PaymentMethodMapper;
import com.ydj.ttswap.entity.PaymentMethodEntity;
import com.ydj.ttswap.service.PaymentMethodService;


@Service("paymentMethodService")
public class PaymentMethodServiceImpl extends ServiceImpl<PaymentMethodMapper, PaymentMethodEntity> implements PaymentMethodService {

    @Autowired
    PaymentMethodMapper methodMapper;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<PaymentMethodEntity> wrapper = new QueryWrapper<>();
        String id= (String) params.get("apply");
        if (!StringUtils.isEmpty(id)){
            wrapper.eq("lx",id);
        }
        IPage<PaymentMethodEntity> page = this.page(
                new Query<PaymentMethodEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

    @Override
    public List<PaymentMethodEntity> selectList(String js) {

        QueryWrapper<PaymentMethodEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("zt",1);
        if (js.equals("user")){
            wrapper.ne("lb",2);
        }
        return methodMapper.selectList(wrapper);
    }

}