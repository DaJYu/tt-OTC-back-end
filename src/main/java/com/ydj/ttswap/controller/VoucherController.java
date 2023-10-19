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

import com.ydj.ttswap.entity.VoucherEntity;
import com.ydj.ttswap.service.VoucherService;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.utils.R;



/**
 * 交易凭据
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
@RestController
@RequestMapping("otc/voucher")
public class VoucherController {
    @Autowired
    private VoucherService voucherService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = voucherService.queryPage(params);

        return R.ok().put("data", page);
    }
    @RequestMapping("/user/list")
    public R lists(@RequestParam Map<String, Object> params){
        List<VoucherEntity> list = voucherService.lists(params);

        return R.ok().put("data", list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{ddid}")
    public R info(@PathVariable("ddid") Integer ddid){
		VoucherEntity voucher = voucherService.getById(ddid);

        return R.ok().put("voucher", voucher);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody VoucherEntity voucher){
		voucherService.save(voucher);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody VoucherEntity voucher){
		voucherService.updateById(voucher);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ddids){
		voucherService.removeByIds(Arrays.asList(ddids));

        return R.ok();
    }

}
