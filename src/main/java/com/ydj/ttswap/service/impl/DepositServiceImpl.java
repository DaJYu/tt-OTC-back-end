package com.ydj.ttswap.service.impl;

import com.ydj.ttswap.vo.DepositRecordVo;
import com.ydj.ttswap.vo.DepositVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.utils.Query;

import com.ydj.ttswap.mapper.DepositMapper;
import com.ydj.ttswap.entity.DepositEntity;
import com.ydj.ttswap.service.DepositService;
import org.springframework.util.StringUtils;


@Service("depositService")
public class DepositServiceImpl extends ServiceImpl<DepositMapper, DepositEntity> implements DepositService {

    @Autowired
    DepositMapper depositMapper;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<DepositVo> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(params.get("fbid"))){
            wrapper.eq("a.fbid",params.get("fbid"));
        }
        if (!StringUtils.isEmpty(params.get("bsid"))){
            wrapper.eq("a.bsid",params.get("bsid"));
        }
        IPage<DepositVo> page =depositMapper.selectPages(new Query<DepositVo>().getPage(params),wrapper);
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(String js, Map<String, Object> params) {
        QueryWrapper<DepositVo> wrapper = new QueryWrapper<>();
        wrapper.eq("a.bsid",params.get("bsid"));
        if (!StringUtils.isEmpty(params.get("fbid"))){
            wrapper.eq("a.fbid",params.get("fbid"));
        }
        IPage<DepositVo> page =depositMapper.selectPages(new Query<DepositVo>().getPage(params),wrapper);
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPages(Map<String, Object> params) {
        QueryWrapper<DepositRecordVo> wrapper = new QueryWrapper<>();
        wrapper.eq("a.bzjid",params.get("bzjid"));
        wrapper.orderByDesc("a.cjsj");
        IPage<DepositRecordVo> page =depositMapper.recordSelectPages(new Query<DepositRecordVo>().getPage(params),wrapper);
        return new PageUtils(page);
    }

}