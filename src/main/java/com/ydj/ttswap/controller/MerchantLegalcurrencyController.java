package com.ydj.ttswap.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ydj.ttswap.entity.CoinDealerApplyEntity;
import com.ydj.ttswap.entity.DepositEntity;
import com.ydj.ttswap.entity.DepositHistoryEntity;
import com.ydj.ttswap.service.CoinDealerApplyService;
import com.ydj.ttswap.service.DepositHistoryService;
import com.ydj.ttswap.service.DepositService;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.utils.R;
import com.ydj.ttswap.vo.MerchantLegalcurrencyVo;
import com.ydj.ttswap.vo.ParameterVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ydj.ttswap.entity.MerchantLegalcurrencyEntity;
import com.ydj.ttswap.service.MerchantLegalcurrencyService;



/**
 * 币商法币表
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-04-21 15:31:21
 */
@RestController
@RequestMapping("otc/legalcurrency/merchant")
public class MerchantLegalcurrencyController {
    @Autowired
    private MerchantLegalcurrencyService merchantLegalcurrencyService;
    @Autowired
    private DepositService depositService;
    @Autowired
    private DepositHistoryService depositHistoryService;
    @Autowired
    private CoinDealerApplyService coinDealerApplyService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = merchantLegalcurrencyService.queryPage(params);

        return R.ok().put("data", page);
    }
    /**
     * 列表
     */
    @RequestMapping("/listSel")
    public R listSel(@RequestParam("id") String id){
        List<MerchantLegalcurrencyVo> page = merchantLegalcurrencyService.listSel(id);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		MerchantLegalcurrencyEntity merchantLegalcurrency = merchantLegalcurrencyService.getById(id);

        return R.ok().put("merchantLegalcurrency", merchantLegalcurrency);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @Transactional(rollbackFor = Exception.class)
    public R save(@RequestBody ParameterVo parameterVo){

        Boolean pd= merchantLegalcurrencyService.selectOne(parameterVo);
        if (pd){
            DepositEntity entity=new DepositEntity();
            entity.setBsid(parameterVo.getBsid());
            entity.setFbid(parameterVo.getFbid());
            entity.setBzj(parameterVo.getBzj());
            entity.setZt(2);
            depositService.save(entity);

            DepositHistoryEntity history=new DepositHistoryEntity();
            BeanUtils.copyProperties(entity,history);
            depositHistoryService.save(history);

            MerchantLegalcurrencyEntity merchantLegalcurrency =new MerchantLegalcurrencyEntity();
            merchantLegalcurrency.setBsid(parameterVo.getBsid());
            merchantLegalcurrency.setFbid(parameterVo.getFbid());
            merchantLegalcurrency.setBzjid(entity.getBzjid());
            merchantLegalcurrency.setZt(2);
            merchantLegalcurrencyService.save(merchantLegalcurrency);

            CoinDealerApplyEntity applyEntity =new CoinDealerApplyEntity();
            applyEntity.setBsqb(parameterVo.getBsid());
            applyEntity.setFbid(parameterVo.getFbid());
            applyEntity.setZfr(parameterVo.getZfr());
            applyEntity.setSkzhid(parameterVo.getZhid());
            applyEntity.setSqlb(2);
            applyEntity.setBzjid(entity.getBzjid());
            applyEntity.setBzj(parameterVo.getBzj());
            coinDealerApplyService.save(applyEntity);
            return R.ok();
        } else {
            return R.error("法币已存在，请不要重复操作！");
        }
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody MerchantLegalcurrencyEntity merchantLegalcurrency){
        merchantLegalcurrency.setGxsj(new Date());
		merchantLegalcurrencyService.updateById(merchantLegalcurrency);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestParam("id") Integer id){
		merchantLegalcurrencyService.removeById(id);

        return R.ok();
    }

}
