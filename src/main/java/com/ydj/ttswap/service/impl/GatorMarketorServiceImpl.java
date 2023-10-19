package com.ydj.ttswap.service.impl;

import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ydj.ttswap.mapper.GatorMarketorMapper;
import com.ydj.ttswap.entity.GatorMarketorEntity;
import com.ydj.ttswap.service.GatorMarketorService;


@Service("gatorMarketorService")
public class GatorMarketorServiceImpl extends ServiceImpl<GatorMarketorMapper, GatorMarketorEntity> implements GatorMarketorService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GatorMarketorEntity> page = this.page(
                new Query<GatorMarketorEntity>().getPage(params),
                new QueryWrapper<GatorMarketorEntity>()
        );

        return new PageUtils(page);
    }

}