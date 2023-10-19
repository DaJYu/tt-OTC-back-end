package com.ydj.ttswap.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ydj.ttswap.entity.*;
import com.ydj.ttswap.mapper.CoinDealerApplyMapper;
import com.ydj.ttswap.mapper.MerchantLegalcurrencyMapper;
import com.ydj.ttswap.service.*;
import com.ydj.ttswap.utils.MathUtils;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.utils.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("coinDealerApplyService")
public class CoinDealerApplyServiceImpl extends ServiceImpl<CoinDealerApplyMapper, CoinDealerApplyEntity> implements CoinDealerApplyService {

    @Autowired
    CoinDealerApplyMapper coinDealerApplyMapper;
    @Autowired
    private CoinDealerService coinDealerService;
    @Autowired
    private CoinDealerApplyService coinDealerApplyService;
    @Autowired
    private DepositService depositService;
    @Autowired
    private DepositHistoryService depositHistoryService;
    @Autowired
    MerchantLegalcurrencyMapper mapper;

    @Override
    public PageUtils getApplyList(Map<String, Object> params) {

        QueryWrapper<CoinDealerApplyEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("zt",0);
        String lx= (String) params.get("apply");
        if (!StringUtils.isEmpty(lx)){
            wrapper.eq("sqlb",lx);
        }
        IPage<CoinDealerApplyEntity> page = this.page(
                new Query<CoinDealerApplyEntity>().getPage(params),
                wrapper
        );
        return new PageUtils(page);
    }
    @Override
    public PageUtils getApplyLists(Map<String, Object> params) {

        QueryWrapper<CoinDealerApplyEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("a.bsqb",params.get("bsid"));
        String lx= (String) params.get("apply");
        if (!StringUtils.isEmpty(lx)){
            wrapper.eq("a.sqlb",lx);
        }
        IPage<CoinDealerApplyEntity> page = coinDealerApplyMapper.selectPages(new Query<CoinDealerApplyEntity>().getPage(params),wrapper);
        return new PageUtils(page);
    }

    @Override
    public Long getApplyNum() {
        QueryWrapper<CoinDealerApplyEntity> wrapper = new QueryWrapper<>();
        wrapper
                .select("*")
                .eq("zt",0)
                .last("limit 1");
        Long mun =coinDealerApplyMapper.selectCount(wrapper);
        return mun;
    }

    @Override
    public List selInfo(Integer id) {
//        List page = coinDealerApplyMapper.findByJoin(id);
        return coinDealerApplyMapper.findByJoin(id);
    }

    @Override
    public void updateApply(CoinDealerApplyEntity coinDealerApply) {

        if (coinDealerApply.getZt().equals(1)){
            CoinDealerApplyEntity apply =coinDealerApplyService.getById(coinDealerApply);
            System.out.println("*******"+apply);
            if (coinDealerApply.getSqlb().equals(1)){
                CoinDealerEntity entity=new CoinDealerEntity();
                BeanUtils.copyProperties(apply,entity);
                entity.setZt(1);
                coinDealerService.save(entity);
            }
            if (coinDealerApply.getSqlb().equals(2)){
                DepositEntity entity=new DepositEntity();
                entity.setBzjid(apply.getBzjid());
                entity.setKsyed(apply.getBzj());
                entity.setZt(1);
                entity.setXgsj(new Date());
                depositService.updateById(entity);
                DepositHistoryEntity history=new DepositHistoryEntity();
                history.setBzjid(apply.getBzjid());
                history.setKsyed(apply.getBzj());
                history.setFbid(apply.getFbid());
                history.setZt(1);
                depositHistoryService.save(history);
                UpdateWrapper updateWrapper = new UpdateWrapper();
                updateWrapper.eq("bzjid",apply.getBzjid());
                updateWrapper.set("zt",1);
                updateWrapper.set("gxsj",new Date());
                mapper.update(null,updateWrapper);
            }
            if (coinDealerApply.getSqlb().equals(3)){
                DepositEntity entity=new DepositEntity();
                DepositEntity deposit=depositService.getById(coinDealerApply.getBzjid());
                BeanUtils.copyProperties(deposit,entity);
                entity.setBzj(MathUtils.add(deposit.getBzj(),apply.getBzj()));//  deposit.getBzj()+apply.getBzj());
                entity.setKsyed(MathUtils.add(deposit.getKsyed(),apply.getBzj()));//  deposit.getKsyed()+apply.getBzj());
                depositService.updateById(entity);
                DepositHistoryEntity history=new DepositHistoryEntity();
                BeanUtils.copyProperties(entity,history);
                history.setJned(apply.getBzj());
                depositHistoryService.save(history);
            }
            if (coinDealerApply.getSqlb().equals(4)){
                DepositEntity entity=new DepositEntity();
                DepositEntity deposit=depositService.getById(coinDealerApply.getBzjid());
                BeanUtils.copyProperties(deposit,entity);
                entity.setBzj(MathUtils.sub(deposit.getBzj(),apply.getBzj()));//  deposit.getBzj()-apply.getBzj());
                entity.setKsyed(MathUtils.sub(deposit.getKsyed(),apply.getBzj()));//  deposit.getKsyed()-apply.getBzj());
                depositService.updateById(entity);
                DepositHistoryEntity history=new DepositHistoryEntity();
                BeanUtils.copyProperties(entity,history);
                history.setTcbzj(apply.getBzj());
                depositHistoryService.save(history);
            }
        }
    }

    @Override
    public Boolean getPd(String bsqb) {

        QueryWrapper<CoinDealerApplyEntity> wrapper = new QueryWrapper<>();
        wrapper
                .select("*")
                .eq("bsqb",bsqb)
                .ne("zt",2)
                .last("limit 1");
        Long mun =coinDealerApplyMapper.selectCount(wrapper);
        if (mun==0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public CoinDealerApplyEntity setInfo(String id) {

        QueryWrapper<CoinDealerApplyEntity> wrapper = new QueryWrapper<>();
        wrapper
                .select("*")
                .eq("bsqb",id)
//                .ne("zt",1)
                .orderByDesc("sqsj")
                .last("limit 1");
        CoinDealerApplyEntity mun =coinDealerApplyMapper.selectOne(wrapper);
        return mun;
    }
}
