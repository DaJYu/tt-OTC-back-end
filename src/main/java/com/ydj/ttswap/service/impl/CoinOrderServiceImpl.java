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

import com.ydj.ttswap.mapper.CoinOrderMapper;
import com.ydj.ttswap.entity.CoinOrderEntity;
import com.ydj.ttswap.service.CoinOrderService;


@Service("coinOrderService")
public class CoinOrderServiceImpl extends ServiceImpl<CoinOrderMapper, CoinOrderEntity> implements CoinOrderService {

    @Autowired
    CoinOrderMapper mapper;

    @Override
    public PageUtils queryPage(String lx, Map<String, Object> params) {
        QueryWrapper<CoinOrderEntity> wrapper = new QueryWrapper<>();
        if (lx.equals("cion")){
            wrapper.eq("lz",0);
        } else {
            wrapper.eq("lz",1);
        }
        IPage<CoinOrderEntity> page = this.page(
                new Query<CoinOrderEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

    @Override
    public List<CoinOrderEntity> lists() {
        QueryWrapper<CoinOrderEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("zt",1)
        .eq("lz",0);
        List<CoinOrderEntity> list =mapper.selectList(wrapper);
        return list;
    }

    @Override
    public PageUtils queryPages(String lx, Map<String, Object> params) {
        QueryWrapper<CoinOrderEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("ly",1);
        if (lx.equals("cion")){
            wrapper.eq("lz",0);
        } else {
            wrapper.eq("lz",1);
        }
        IPage<CoinOrderEntity> page = this.page(
                new Query<CoinOrderEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

    @Override
    public Boolean getPd(CoinOrderEntity coinOrder) {
        QueryWrapper<CoinOrderEntity> wrapper = new QueryWrapper<>();
        wrapper
                .select("*")
                .eq("lz",coinOrder.getLz())
                .and(i -> i.eq("hydz",coinOrder.getHydz())
                        .or()
                        .eq("mc",coinOrder.getMc())
                        .or()
                        .eq("qc",coinOrder.getQc()))
                .last("limit 1");
        Long mun =mapper.selectCount(wrapper);
        if (mun==0){
            return true;
        }else {
            return false;
        }
    }


}