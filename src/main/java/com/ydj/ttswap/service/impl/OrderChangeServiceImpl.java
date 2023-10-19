package com.ydj.ttswap.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.utils.Query;

import com.ydj.ttswap.mapper.OrderChangeMapper;
import com.ydj.ttswap.entity.OrderChangeEntity;
import com.ydj.ttswap.service.OrderChangeService;


@Service("orderChangeService")
public class OrderChangeServiceImpl extends ServiceImpl<OrderChangeMapper, OrderChangeEntity> implements OrderChangeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderChangeEntity> page = this.page(
                new Query<OrderChangeEntity>().getPage(params),
                new QueryWrapper<OrderChangeEntity>()
        );

        return new PageUtils(page);
    }

}