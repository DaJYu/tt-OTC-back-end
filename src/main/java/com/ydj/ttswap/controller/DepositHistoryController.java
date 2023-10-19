package com.ydj.ttswap.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ydj.ttswap.entity.DepositHistoryEntity;
import com.ydj.ttswap.service.DepositHistoryService;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.utils.R;



/**
 * 保证金历史记录信息表
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
@RestController
@RequestMapping("otc/deposithistory")
public class DepositHistoryController {
    @Autowired
    private DepositHistoryService depositHistoryService;

    /**
     * 列表
     */
    @RequestMapping("/{jspd}/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = depositHistoryService.queryPage(params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{zhdz}")
    public R info(@PathVariable("zhdz") String zhdz){
		DepositHistoryEntity depositHistory = depositHistoryService.getById(zhdz);

        return R.ok().put("depositHistory", depositHistory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DepositHistoryEntity depositHistory){
		depositHistoryService.save(depositHistory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody DepositHistoryEntity depositHistory){
		depositHistoryService.updateById(depositHistory);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] zhdzs){
		depositHistoryService.removeByIds(Arrays.asList(zhdzs));

        return R.ok();
    }

}
