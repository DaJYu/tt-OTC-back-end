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

import com.ydj.ttswap.entity.UserEmailEntity;
import com.ydj.ttswap.service.UserEmailService;



/**
 * 用户邮箱
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-08-14 18:40:28
 */
@RestController
@RequestMapping("user/useremail")
public class UserEmailController {
    @Autowired
    private UserEmailService userEmailService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userEmailService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		UserEmailEntity userEmail = userEmailService.getById(id);

        return R.ok().put("userEmail", userEmail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody UserEmailEntity userEmail){
		userEmailService.save(userEmail);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody UserEmailEntity userEmail){
		userEmailService.updateById(userEmail);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		userEmailService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
