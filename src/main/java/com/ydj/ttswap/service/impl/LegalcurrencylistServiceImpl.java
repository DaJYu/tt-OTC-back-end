package com.ydj.ttswap.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ydj.ttswap.mapper.LegalcurrencylistMapper;
import com.ydj.ttswap.entity.LegalcurrencylistEntity;
import com.ydj.ttswap.service.LegalcurrencylistService;


@Service("legalcurrencylistService")
public class LegalcurrencylistServiceImpl extends ServiceImpl<LegalcurrencylistMapper, LegalcurrencylistEntity> implements LegalcurrencylistService {

    @Autowired
    LegalcurrencylistMapper mapper;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<LegalcurrencylistEntity> wrapper = new QueryWrapper<>();
        String id= (String) params.get("name");
        if (!StringUtils.isEmpty(id)){
            wrapper.eq("name",id);
        }
        IPage<LegalcurrencylistEntity> page = this.page(
                new Query<LegalcurrencylistEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

    @Override
    public List<LegalcurrencylistEntity> listSel() {

        QueryWrapper<LegalcurrencylistEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("zt",1);

        return mapper.selectList(wrapper);
    }

    @Override
    public List<LegalcurrencylistEntity> bsLists(Map<String, Object> params) {

        QueryWrapper<LegalcurrencylistEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("b.bsid",params.get("bsid"));
        List<LegalcurrencylistEntity> list = mapper.selectLists(wrapper);
        return list;
    }

}