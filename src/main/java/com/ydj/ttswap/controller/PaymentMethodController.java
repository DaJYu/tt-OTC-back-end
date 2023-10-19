package com.ydj.ttswap.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ydj.ttswap.entity.PaymentMethodEntity;
import com.ydj.ttswap.service.PaymentMethodService;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.utils.R;



/**
 * 支付方式信息表
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
@RestController
@RequestMapping("base/paymentmethod")
public class PaymentMethodController {
    @Autowired
    private PaymentMethodService paymentMethodService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = paymentMethodService.queryPage(params);

        return R.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/{jspd}/selectList")
    public R selectList(@PathVariable("jspd") String js){
        List<PaymentMethodEntity> list = paymentMethodService.selectList(js);

        return R.ok().put("data", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		PaymentMethodEntity paymentMethod = paymentMethodService.getById(id);

        return R.ok().put("paymentMethod", paymentMethod);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody PaymentMethodEntity paymentMethod){
		paymentMethodService.save(paymentMethod);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody PaymentMethodEntity legal){
        if (legal.getZt()==0||legal.getZt()==1){
            paymentMethodService.updateById(legal);
        }

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody PaymentMethodEntity legal){
		paymentMethodService.removeById(legal);

        return R.ok();
    }

}
