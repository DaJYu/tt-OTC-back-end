package com.ydj.ttswap.controller;

import java.util.Arrays;
import java.util.Map;

import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ydj.ttswap.entity.GatorMarketorEntity;
import com.ydj.ttswap.service.GatorMarketorService;



/**
 * 门户管理员
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-08-03 13:21:12
 */
@RestController
@RequestMapping("admin/gatormarketor")
public class GatorMarketorController {
    @Autowired
    private GatorMarketorService gatorMarketorService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = gatorMarketorService.queryPage(params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		GatorMarketorEntity gatorMarketor = gatorMarketorService.getById(id);

        return R.ok().put("data", gatorMarketor);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody GatorMarketorEntity gatorMarketor){
		gatorMarketorService.save(gatorMarketor);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody GatorMarketorEntity gatorMarketor){
		gatorMarketorService.updateById(gatorMarketor);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		gatorMarketorService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
