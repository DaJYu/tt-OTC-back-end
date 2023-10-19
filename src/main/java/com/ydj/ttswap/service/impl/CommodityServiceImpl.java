package com.ydj.ttswap.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.utils.Query;
import com.ydj.ttswap.vo.OtcListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ydj.ttswap.mapper.CommodityMapper;
import com.ydj.ttswap.entity.CommodityEntity;
import com.ydj.ttswap.service.CommodityService;


@Service("commodityService")
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, CommodityEntity> implements CommodityService {

    @Autowired
    CommodityMapper mapper;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        QueryWrapper<OtcListVo> wrapper = new QueryWrapper<>();
        String id= (String) params.get("cslx");
        String bz= (String) params.get("bzmc");
        String zffsid= (String) params.get("zffs");
//        System.out.println(zffsid);
        if (!StringUtils.isEmpty(id)){
            wrapper.eq("a.cslx",id);
        } else {
            wrapper.eq("a.cslx",1);
        }
        if (!StringUtils.isEmpty(bz)){
            wrapper.eq("a.bzmc",bz);
        }
        if (!StringUtils.isEmpty(zffsid)){
            wrapper.in("b.zffsid",zffsid);
        }
        wrapper.gt("a.ksyed","a.zdxe");
        wrapper.groupBy("a.spid");
        IPage<OtcListVo> page = mapper.pages(
                new Query<OtcListVo>().getPage(params),
                wrapper
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPages(Map<String, Object> params) {

        QueryWrapper<CommodityEntity> wrapper = new QueryWrapper<>();
        String cjr = (String) params.get("bsqb");
        wrapper.eq("a.cjr",cjr);
        String id= (String) params.get("cslx");
        String xnb= (String) params.get("xnb");
        String zt= (String) params.get("zt");
        if (!StringUtils.isEmpty(id)){
            wrapper.eq("a.cslx",id);
        }
        if (!StringUtils.isEmpty(zt)){
            wrapper.eq("a.zt",params.get("zt"));
        }
        if (!StringUtils.isEmpty(xnb)){
            wrapper.eq("a.bzmc",xnb);
        }
        wrapper.orderByDesc("a.gxsj");
        IPage<CommodityEntity> page = mapper.page(
                new Query<CommodityEntity>().getPage(params),
                wrapper
        );
        return new PageUtils(page);
    }

    @Override
    public List<CommodityEntity> statusList() {
        QueryWrapper<CommodityEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("zt",1);
        wrapper.isNotNull("jzsj");
        List<CommodityEntity> list= mapper.selectList(wrapper);
        return list;
    }

    @Override
    public void productDeadline(CommodityEntity i) {
        i.setZt(0);
        i.setGxsj(new Date());
        mapper.updateById(i);
    }

}