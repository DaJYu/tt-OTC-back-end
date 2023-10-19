package com.ydj.ttswap.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ydj.ttswap.entity.LegalCurrencyPaymentMethodEntity;
import com.ydj.ttswap.entity.LegalcurrencylistEntity;
import com.ydj.ttswap.service.LegalCurrencyPaymentMethodService;
import com.ydj.ttswap.service.LegalcurrencylistService;
import com.ydj.ttswap.vo.LegalCurrencyVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ydj.ttswap.entity.LegalCurrencyEntity;
import com.ydj.ttswap.service.LegalCurrencyService;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.utils.R;



/**
 * 法币信息表
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
@RestController
@RequestMapping("base/legalcurrency")
public class LegalCurrencyController {
    @Autowired
    private LegalCurrencyService legalCurrencyService;
    @Autowired
    private LegalCurrencyPaymentMethodService legalCurrencyPaymentMethodService;
    @Autowired
    private LegalcurrencylistService legalcurrencylistService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(){
        List<LegalCurrencyEntity> list = legalCurrencyService.queryPage();

        return R.ok().put("data", list);
    }
    /**
     * 列表
     */
    @RequestMapping("/lists")
    public R lists(@RequestParam Map<String, Object> params){
        PageUtils page = legalcurrencylistService.queryPage(params);

        return R.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/bs/lists")
    public R bsLists(@RequestParam Map<String, Object> params){
        List<LegalcurrencylistEntity> list = legalcurrencylistService.bsLists(params);

        return R.ok().put("data", list);
    }
    /**
     * 列表
     */
    @RequestMapping("/listSel")
    public R listSel(){
        List<LegalcurrencylistEntity> list = legalcurrencylistService.listSel();

        return R.ok().put("data", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		LegalCurrencyEntity legalCurrency = legalCurrencyService.getById(id);

        return R.ok().put("legalCurrency", legalCurrency);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody LegalCurrencyEntity legalCurrency){
		legalCurrencyService.save(legalCurrency);
        return R.ok();
    }

    /**
     * 保存
     */
    @RequestMapping("/save1")
    public R save1(@RequestBody LegalCurrencyVo vo){
        LegalCurrencyEntity legalCurrency=new LegalCurrencyEntity();
        BeanUtils.copyProperties(vo,legalCurrency);
        if(vo.getSfsxf()==1){
            legalCurrency.setFl(vo.getFlz() / 100);
        }
        legalCurrencyService.save(legalCurrency);

        List<Long> id=vo.getZffsid();
        List<LegalCurrencyPaymentMethodEntity> list = id.stream().map(zfid ->{
            LegalCurrencyPaymentMethodEntity mapping=new LegalCurrencyPaymentMethodEntity();
            mapping.setFbid(legalCurrency.getFbid());
            mapping.setZffsid(zfid);
            return mapping;
        }).collect(Collectors.toList());
        legalCurrencyPaymentMethodService.saveBatch(list);
        return R.ok();
    }
    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody LegalCurrencyVo vo){
        LegalCurrencyEntity legalCurrency=new LegalCurrencyEntity();
        BeanUtils.copyProperties(vo,legalCurrency);
        if(vo.getSfsxf()==1){
            legalCurrency.setFl(vo.getFlz() / 100);
        }
		legalCurrencyService.updateById(legalCurrency);

        List<Long> id=vo.getZffsid();
        legalCurrencyPaymentMethodService.removeById(vo.getFbid());
        List<LegalCurrencyPaymentMethodEntity> list = id.stream().map(zfid ->{
            LegalCurrencyPaymentMethodEntity mapping=new LegalCurrencyPaymentMethodEntity();
            mapping.setFbid(vo.getFbid());
            mapping.setZffsid(zfid);
            return mapping;
        }).collect(Collectors.toList());
        legalCurrencyPaymentMethodService.saveBatch(list);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody LegalCurrencyEntity legal){
        legalCurrencyService.removeById(legal);

        return R.ok();
    }

    /**
     * 状态变更
     */
    @RequestMapping("/status")
    public R status(@RequestBody LegalCurrencyEntity legal){
        if (legal.getZt()==0||legal.getZt()==1){
            legalCurrencyService.updateById(legal);
        }

        return R.ok();
    }

}
