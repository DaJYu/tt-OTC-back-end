package com.ydj.ttswap.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ydj.ttswap.entity.CoinDealerEntity;
import com.ydj.ttswap.mapper.CoinDealerMapper;
import com.ydj.ttswap.service.CoinDealerService;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;

@Service("coinDealerService")
public class CoinDealerServiceImpl extends ServiceImpl<CoinDealerMapper, CoinDealerEntity> implements CoinDealerService {

    @Autowired
    CoinDealerMapper coinDealerMapper;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        QueryWrapper<CoinDealerEntity> wrapper = new QueryWrapper<>();
        String id= (String) params.get("name");
        if (!StringUtils.isEmpty(id)){
            wrapper.eq("bsqb",id);
        }
        IPage<CoinDealerEntity> page = this.page(
                new Query<CoinDealerEntity>().getPage(params),
                wrapper
        );
        return new PageUtils(page);
    }

    @Override
    public void getStateChanges(Map<String, Object> params) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("bsqb",params.get("bsqb"));
        updateWrapper.set("zt",params.get("status"));
        updateWrapper.set("gxsj",new Date());
        coinDealerMapper.update(null,updateWrapper);
    }

    @Override
    public CoinDealerEntity getBsqb(String bsqb) {
        QueryWrapper<CoinDealerEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("bsqb",bsqb);

        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public Long role(String qbdz) {
        QueryWrapper<CoinDealerEntity> wrapper = new QueryWrapper<>();
        wrapper
                .select("*")
                .eq("bsqb",qbdz)
                .ne("zt",2)
                .last("limit 1");
        Long mun =this.baseMapper.selectCount(wrapper);
        return mun;
    }
}
