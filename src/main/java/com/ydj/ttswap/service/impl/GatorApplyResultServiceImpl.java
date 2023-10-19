package com.ydj.ttswap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.utils.Query;

import com.ydj.ttswap.mapper.GatorApplyResultMapper;
import com.ydj.ttswap.entity.GatorApplyResultEntity;
import com.ydj.ttswap.service.GatorApplyResultService;


@Service("gatorApplyResultService")
public class GatorApplyResultServiceImpl extends ServiceImpl<GatorApplyResultMapper, GatorApplyResultEntity> implements GatorApplyResultService {

    @Autowired
    GatorApplyResultMapper resultMapper;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<GatorApplyResultEntity> wrapper = new QueryWrapper<>();
        if (!params.get("apply").toString().isEmpty()){
            wrapper.eq("qbdz",params.get("apply"));
        }
        IPage<GatorApplyResultEntity> page = this.page(
                new Query<GatorApplyResultEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }


    @Override
    public GatorApplyResultEntity getResul(String dz) {
        QueryWrapper<GatorApplyResultEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("dz",dz)
                .orderByDesc("id").last("limit 1");
        return (GatorApplyResultEntity) resultMapper.selectList(wrapper);

    }

    @Override
    public Long getApplyNum() {
        QueryWrapper<GatorApplyResultEntity> wrapper = new QueryWrapper<>();
        wrapper
                .select("*")
                .eq("zt",0)
                .last("limit 1");
        Long mun =resultMapper.selectCount(wrapper);
        return mun;
    }

    @Override
    public Boolean getPd(String qbdz) {
        QueryWrapper<GatorApplyResultEntity> wrapper = new QueryWrapper<>();
        wrapper
                .select("*")
                .eq("qbdz",qbdz)
                .ne("zt",2)
                .last("limit 1");
        Long mun =resultMapper.selectCount(wrapper);
        if (mun==0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public GatorApplyResultEntity getId(String qbdz) {
        QueryWrapper<GatorApplyResultEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("qbdz",qbdz);
        GatorApplyResultEntity entity = resultMapper.selectOne(wrapper);
        return entity;
    }

}