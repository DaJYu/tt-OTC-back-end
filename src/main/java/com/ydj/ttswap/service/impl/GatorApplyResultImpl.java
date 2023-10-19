//package com.ydj.ttswap.service.impl;
//
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.ydj.ttswap.entity.GatorApplyResultEntity;
//import com.ydj.ttswap.mapper.GatorApplyResultMapper;
//import com.ydj.ttswap.service.GatorApplyResultService;
//import com.ydj.ttswap.utils.PageUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//
//import java.util.List;
//import java.util.Map;
//
//@Service("gatorApplyResultService")
//public class GatorApplyResultImpl extends ServiceImpl<GatorApplyResultMapper, GatorApplyResultEntity> implements GatorApplyResultService {
//
//
//    @Autowired
//    GatorApplyResultMapper resultMapper;
//
//    @Override
//    public GatorApplyResultEntity getResul(String dz) {
//        QueryWrapper<GatorApplyResultEntity> wrapper = new QueryWrapper<>();
//            wrapper.eq("dz",dz)
//            .orderByDesc("id").last("limit 1");
//        return (GatorApplyResultEntity) resultMapper.selectList(wrapper);
//
//    }
//
////    @Override
////    public void saves(Map<String,Object> params) {
//////        System.out.println(params);
////        GatorApplyResultEntity gatorApplyResult =  new GatorApplyResultEntity();
////        gatorApplyResult.setDz((String) params.get("dz"));
////        gatorApplyResult.setYj((String) params.get("yj"));
////        gatorApplyResult.setShy((String) params.get("shy"));
////        resultMapper.insert(gatorApplyResult);
////    }
//
//    @Override
//    public PageUtils queryPage(Map<String, Object> params) {
//        return null;
//    }
//
//}
