package com.ydj.ttswap.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import com.ydj.ttswap.entity.CoinDealerApplyEntity;
import com.ydj.ttswap.entity.DepositHistoryEntity;
import com.ydj.ttswap.entity.GatorOtcIncomeDrawRecordEntity;
import com.ydj.ttswap.service.CoinDealerApplyService;
import com.ydj.ttswap.service.DepositHistoryService;
import com.ydj.ttswap.service.GatorOtcIncomeDrawRecordService;
import com.ydj.ttswap.utils.MathUtils;
import com.ydj.ttswap.vo.ParameterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ydj.ttswap.entity.DepositEntity;
import com.ydj.ttswap.service.DepositService;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.utils.R;



/**
 * 保证金信息表
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
@RestController
@RequestMapping("otc/deposit")
public class DepositController {
    @Autowired
    private DepositService depositService;
    @Autowired
    private DepositHistoryService depositHistoryService;
    @Autowired
    private CoinDealerApplyService coinDealerApplyService;
    @Autowired
    private GatorOtcIncomeDrawRecordService gatorOtcIncomeDrawRecordService;

    /**
     * 列表
     */
    @RequestMapping("/{jspd}/list")
    public R list(@PathVariable("jspd") String js,@RequestParam Map<String, Object> params){
        PageUtils page;
        if (js.equals("merchant")){
            page = depositService.queryPage(js,params);
        } else {
            page = depositService.queryPage(params);
        }
        return R.ok().put("data", page);
    }

    /**
     * 历史记录列表
     */
    @RequestMapping("/{jspd}/record")
    public R record(@RequestParam Map<String, Object> params){
        PageUtils page = depositService.queryPages(params);
        return R.ok().put("data", page);
    }
    /**
     * 续交保证金
     */
    @RequestMapping("/{jspd}/renew")
    @Transactional(rollbackFor = Exception.class)
    public R renew(@RequestBody ParameterVo parameterVo){
        DepositHistoryEntity history=new DepositHistoryEntity();
        history.setBsid(parameterVo.getBsid());
        history.setFbid(parameterVo.getFbid());
        history.setBzjid(parameterVo.getBzjid());
        history.setJned(parameterVo.getBzj());
        depositHistoryService.save(history);

        CoinDealerApplyEntity applyEntity =new CoinDealerApplyEntity();
        applyEntity.setBsqb(parameterVo.getBsid());
        applyEntity.setFbid(parameterVo.getFbid());
        applyEntity.setZfr(parameterVo.getZfr());
        applyEntity.setSkzhid(parameterVo.getZhid());
        applyEntity.setSqlb(3);
        applyEntity.setBzj(parameterVo.getBzj());
        applyEntity.setBzjid(parameterVo.getBzjid());
        coinDealerApplyService.save(applyEntity);
        return R.ok();
    }


    /**
     * 退缴保证金（refund）
     */
    @RequestMapping("/{jspd}/refund")
    @Transactional(rollbackFor = Exception.class)
    public R refund(@RequestBody ParameterVo parameterVo){
        DepositHistoryEntity history=new DepositHistoryEntity();
        history.setBsid(parameterVo.getBsid());
        history.setFbid(parameterVo.getFbid());
        history.setBzjid(parameterVo.getBzjid());
        history.setTcbzj(parameterVo.getBzj());
        depositHistoryService.save(history);

        CoinDealerApplyEntity applyEntity =new CoinDealerApplyEntity();
        applyEntity.setBsqb(parameterVo.getBsid());
        applyEntity.setFbid(parameterVo.getFbid());
        applyEntity.setZfr(parameterVo.getZfr());
        applyEntity.setSkzhid(parameterVo.getZhid());
        applyEntity.setSqlb(4);
        applyEntity.setBzj(parameterVo.getBzj());
        applyEntity.setBzjid(parameterVo.getBzjid());
        coinDealerApplyService.save(applyEntity);
        return R.ok();
    }
    /**
     * 信息
     */
    @RequestMapping("/{jspd}/info/{id}")
    public R info(@PathVariable("id") Integer id){
		DepositEntity deposit = depositService.getById(id);

        return R.ok().put("deposit", deposit);
    }

    /**
     * 保存
     */
    @RequestMapping("/{jspd}/save")
    public R save(@RequestBody DepositEntity deposit){
		depositService.save(deposit);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/{jspd}/update")
    public R update(@RequestBody DepositEntity deposit){
        deposit.setXgsj(new Date());
		depositService.updateById(deposit);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/{jspd}/delete")
    public R delete(@RequestBody DepositEntity deposit){
		depositService.removeById(deposit);

        return R.ok();
    }

    /**
     * 提取手续费
     */
    @RequestMapping("/draw")
    @Transactional(rollbackFor = Exception.class)
    public R draw(@RequestBody ParameterVo vo){
        DepositEntity deposit = depositService.getById(vo.getBzjid());
        DepositEntity gx = new DepositEntity();
        Double sxf = MathUtils.sub(deposit.getKcsxf(),deposit.getTqsxf());// deposit.getKcsxf() - deposit.getTqsxf();
        Double wgf = MathUtils.sub(deposit.getKcwgfy(),deposit.getTqwgfy());// deposit.getKcwgfy() - deposit.getTqwgfy();

        gx.setTqsxf(MathUtils.add(deposit.getTqsxf(),sxf));//deposit.getTqsxf() + sxf);
        gx.setTqwgfy(MathUtils.add(deposit.getTqwgfy(),wgf));//deposit.getTqwgfy() + wgf);
        gx.setBzjid(deposit.getBzjid());
        gx.setXgsj(new Date());
        depositService.updateById(gx);

        GatorOtcIncomeDrawRecordEntity gt = new GatorOtcIncomeDrawRecordEntity();
        gt.setBzjid(deposit.getBzjid());
        gt.setFbid(deposit.getFbid());
        gt.setSxfje(sxf);
        gt.setWgfje(wgf);
        gatorOtcIncomeDrawRecordService.save(gt);
        return R.ok();
    }
}
