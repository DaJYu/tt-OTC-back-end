package com.ydj.ttswap.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ydj.ttswap.entity.LegalCurrencyEntity;
import com.ydj.ttswap.entity.LegalcurrencylistEntity;
import com.ydj.ttswap.mapper.CoinDealerMapper;
import com.ydj.ttswap.mapper.LegalCurrencyMapper;
import com.ydj.ttswap.service.CoinDealerService;
import com.ydj.ttswap.service.LegalCurrencyService;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.utils.Query;
import com.ydj.ttswap.vo.LegalCurrencyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service("LegalCurrencyService")
public class LegalCurrencyServiceImpl extends ServiceImpl<LegalCurrencyMapper, LegalCurrencyEntity> implements LegalCurrencyService {

    @Autowired
    LegalCurrencyMapper legalCurrencyMapper;

    @Override
    public List<LegalCurrencyEntity> queryPage() {

        QueryWrapper<LegalCurrencyEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("zt",1);

        return legalCurrencyMapper.selectList(wrapper);
    }

    @Override
    public List<LegalcurrencylistEntity> listSel() {
        return null;
    }

    @Override
    public PageUtils queryPages(Map<String, Object> params) {

        QueryWrapper<LegalCurrencyVo> wrapper = new QueryWrapper<>();
        String id= (String) params.get("name");
        if (!StringUtils.isEmpty(id)){
            wrapper.eq("name",id);
        }
        return (PageUtils) legalCurrencyMapper.findByJoin(params,wrapper);
    }

//    @Override
//    public void LCstateChanges(Map<String, Object> params) {
//
//    }

//    @Override
//    public void getStateChanges(Map<String, Object> params) {
//        UpdateWrapper updateWrapper = new UpdateWrapper();
//        updateWrapper.eq("bsqb",params.get("bsqb"));
//        updateWrapper.set("zt",params.get("status"));
//        legalCurrencyMapper.update(null,updateWrapper);
//    }

}
