package com.ydj.ttswap.service.impl;

import com.ydj.ttswap.entity.CoinDealerApplyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.utils.Query;

import com.ydj.ttswap.mapper.DepositHistoryMapper;
import com.ydj.ttswap.entity.DepositHistoryEntity;
import com.ydj.ttswap.service.DepositHistoryService;
import org.springframework.util.StringUtils;


@Service("depositHistoryService")
public class DepositHistoryServiceImpl extends ServiceImpl<DepositHistoryMapper, DepositHistoryEntity> implements DepositHistoryService {

    @Autowired
    DepositHistoryMapper mapper;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        QueryWrapper<DepositHistoryEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("a.bsid",params.get("bsid"));
        String lx= (String) params.get("fbid");
        if (!StringUtils.isEmpty(lx)){
            wrapper.eq("a.fbid",lx);
        }
        wrapper.orderByDesc("cjsj");
        IPage<DepositHistoryEntity> page = mapper.selectPages(new Query<DepositHistoryEntity>().getPage(params),wrapper);

        return new PageUtils(page);
    }

}