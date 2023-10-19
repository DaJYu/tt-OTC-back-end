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

import com.ydj.ttswap.entity.GatorOtcIncomeDrawRecordEntity;
import com.ydj.ttswap.service.GatorOtcIncomeDrawRecordService;



/**
 * 门户OTC收益提取记录表
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-07-19 15:01:45
 */
@RestController
@RequestMapping("otc/gatorotcincomedrawrecord")
public class GatorOtcIncomeDrawRecordController {
    @Autowired
    private GatorOtcIncomeDrawRecordService gatorOtcIncomeDrawRecordService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = gatorOtcIncomeDrawRecordService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{bzjid}")
    public R info(@PathVariable("bzjid") Integer bzjid){
		GatorOtcIncomeDrawRecordEntity gatorOtcIncomeDrawRecord = gatorOtcIncomeDrawRecordService.getById(bzjid);

        return R.ok().put("gatorOtcIncomeDrawRecord", gatorOtcIncomeDrawRecord);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody GatorOtcIncomeDrawRecordEntity gatorOtcIncomeDrawRecord){
		gatorOtcIncomeDrawRecordService.save(gatorOtcIncomeDrawRecord);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody GatorOtcIncomeDrawRecordEntity gatorOtcIncomeDrawRecord){
		gatorOtcIncomeDrawRecordService.updateById(gatorOtcIncomeDrawRecord);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] bzjids){
		gatorOtcIncomeDrawRecordService.removeByIds(Arrays.asList(bzjids));

        return R.ok();
    }

}
