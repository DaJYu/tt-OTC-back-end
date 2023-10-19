package com.ydj.ttswap.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ydj.ttswap.entity.OrderChangeEntity;
import com.ydj.ttswap.service.OrderChangeService;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.utils.R;



/**
 * 订单变更信息表
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
@RestController
@RequestMapping("ttswap/orderchange")
public class OrderChangeController {
    @Autowired
    private OrderChangeService orderChangeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderChangeService.queryPage(params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{ddid}")
    public R info(@PathVariable("ddid") Integer ddid){
		OrderChangeEntity orderChange = orderChangeService.getById(ddid);

        return R.ok().put("orderChange", orderChange);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody OrderChangeEntity orderChange){
		orderChangeService.save(orderChange);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody OrderChangeEntity orderChange){
		orderChangeService.updateById(orderChange);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ddids){
		orderChangeService.removeByIds(Arrays.asList(ddids));

        return R.ok();
    }

}
