package com.ydj.ttswap.service.impl;

import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ydj.ttswap.mapper.GatorOtcIncomeDrawRecordMapper;
import com.ydj.ttswap.entity.GatorOtcIncomeDrawRecordEntity;
import com.ydj.ttswap.service.GatorOtcIncomeDrawRecordService;


@Service("gatorOtcIncomeDrawRecordService")
public class GatorOtcIncomeDrawRecordServiceImpl extends ServiceImpl<GatorOtcIncomeDrawRecordMapper, GatorOtcIncomeDrawRecordEntity> implements GatorOtcIncomeDrawRecordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GatorOtcIncomeDrawRecordEntity> page = this.page(
                new Query<GatorOtcIncomeDrawRecordEntity>().getPage(params),
                new QueryWrapper<GatorOtcIncomeDrawRecordEntity>()
        );

        return new PageUtils(page);
    }

}