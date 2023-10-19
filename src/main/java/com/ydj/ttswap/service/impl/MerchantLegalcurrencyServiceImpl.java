package com.ydj.ttswap.service.impl;

import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.utils.Query;
import com.ydj.ttswap.vo.MerchantLegalcurrencyVo;
import com.ydj.ttswap.vo.ParameterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ydj.ttswap.mapper.MerchantLegalcurrencyMapper;
import com.ydj.ttswap.entity.MerchantLegalcurrencyEntity;
import com.ydj.ttswap.service.MerchantLegalcurrencyService;
import org.springframework.util.StringUtils;


@Service("merchantLegalcurrencyService")
public class MerchantLegalcurrencyServiceImpl extends ServiceImpl<MerchantLegalcurrencyMapper, MerchantLegalcurrencyEntity> implements MerchantLegalcurrencyService {

    @Autowired
    MerchantLegalcurrencyMapper mapper;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        QueryWrapper<MerchantLegalcurrencyVo> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(params.get("fbid"))){
            wrapper.eq("a.fbid",params.get("fbid"));
        }
        if (!StringUtils.isEmpty(params.get("bsid"))){
            wrapper.eq("a.bsid",params.get("bsid"));
        }
        wrapper.eq("a.ljsc",0);
        IPage<MerchantLegalcurrencyVo> page =mapper.selectPages(new Query<MerchantLegalcurrencyVo>().getPage(params),wrapper);
        return new PageUtils(page);
    }

    @Override
    public List<MerchantLegalcurrencyVo> listSel(String id) {
        QueryWrapper<MerchantLegalcurrencyVo> wrapper = new QueryWrapper<>();
        wrapper.eq("a.bsid",id)
                .groupBy("a.fbid");
        List<MerchantLegalcurrencyVo> list = mapper.selectLists(wrapper);
        return list;
    }

    @Override
    public Boolean selectOne(ParameterVo parameterVo) {

        QueryWrapper<MerchantLegalcurrencyEntity> wrapper = new QueryWrapper<>();
        wrapper
                .select("*")
                .eq("fbid",parameterVo.getFbid())
                .eq("bsid",parameterVo.getBsid())
                .last("limit 1");
        Long mun =mapper.selectCount(wrapper);
        if (mun==0){
            return true;
        } else {
            return false;
        }
    }

}