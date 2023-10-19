package com.ydj.ttswap.service.impl;

import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ydj.ttswap.mapper.GatorInfoMapper;
import com.ydj.ttswap.entity.GatorInfoEntity;
import com.ydj.ttswap.service.GatorInfoService;


@Service("gatorInfoService")
public class GatorInfoServiceImpl extends ServiceImpl<GatorInfoMapper, GatorInfoEntity> implements GatorInfoService {


    @Autowired
    GatorInfoMapper mapper;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        QueryWrapper<GatorInfoEntity> wrapper = new QueryWrapper<>();
        if (!params.get("name").toString().isEmpty()){
            wrapper.eq("jc",params.get("name"));
        }
        IPage<GatorInfoEntity> page = this.page(
                new Query<GatorInfoEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

    @Override
    public GatorInfoEntity getId(String qbdz) {
        QueryWrapper<GatorInfoEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("qbdz",qbdz);
        GatorInfoEntity entity = mapper.selectOne(wrapper);
        return entity;
    }

    @Override
    public PageUtils partners(Map<String, Object> params) {
        QueryWrapper<GatorInfoEntity> wrapper = new QueryWrapper<>();
            wrapper.eq("hzhb",1)
                    .eq("zt",1);
        IPage<GatorInfoEntity> page = this.page(
                new Query<GatorInfoEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

    @Override
    public Long role(String qbdz) {
        QueryWrapper<GatorInfoEntity> wrapper = new QueryWrapper<>();
        wrapper
                .select("*")
                .eq("qbdz",qbdz)
                .ne("zt",2)
                .last("limit 1");
        Long mun =this.baseMapper.selectCount(wrapper);
        return mun;
    }

}