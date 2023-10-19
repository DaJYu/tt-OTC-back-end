package com.ydj.ttswap.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ydj.ttswap.entity.LegalCurrencyPaymentMethodEntity;
import com.ydj.ttswap.service.LegalCurrencyPaymentMethodService;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.utils.R;



/**
 * 法币支付方式映射表
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
@RestController
@RequestMapping("ttswap/legalcurrencypaymentmethod")
public class LegalCurrencyPaymentMethodController {
    @Autowired
    private LegalCurrencyPaymentMethodService legalCurrencyPaymentMethodService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = legalCurrencyPaymentMethodService.queryPage(params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{fbid}")
    public R info(@PathVariable("fbid") Integer fbid){
		LegalCurrencyPaymentMethodEntity legalCurrencyPaymentMethod = legalCurrencyPaymentMethodService.getById(fbid);

        return R.ok().put("legalCurrencyPaymentMethod", legalCurrencyPaymentMethod);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody LegalCurrencyPaymentMethodEntity legalCurrencyPaymentMethod){
		legalCurrencyPaymentMethodService.save(legalCurrencyPaymentMethod);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody LegalCurrencyPaymentMethodEntity legalCurrencyPaymentMethod){
		legalCurrencyPaymentMethodService.updateById(legalCurrencyPaymentMethod);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] fbids){
		legalCurrencyPaymentMethodService.removeByIds(Arrays.asList(fbids));

        return R.ok();
    }

}
