package com.ydj.ttswap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.utils.Query;

import com.ydj.ttswap.mapper.VoucherMapper;
import com.ydj.ttswap.entity.VoucherEntity;
import com.ydj.ttswap.service.VoucherService;


@Service("voucherService")
public class VoucherServiceImpl extends ServiceImpl<VoucherMapper, VoucherEntity> implements VoucherService {

    @Autowired
    VoucherMapper voucherMapper;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<VoucherEntity> page = this.page(
                new Query<VoucherEntity>().getPage(params),
                new QueryWrapper<VoucherEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<VoucherEntity> lists(Map<String, Object> params) {
        QueryWrapper<VoucherEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("ddid",params.get("ddid"));
        return voucherMapper.selectList(wrapper);
    }

}