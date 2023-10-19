package com.ydj.ttswap.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
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

import com.ydj.ttswap.entity.GatorInfoEntity;
import com.ydj.ttswap.service.GatorInfoService;



/**
 * 门户信息表
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-06-11 21:51:38
 */
@RestController
@RequestMapping("admin/gator")
public class GatorInfoController {
    @Autowired
    private GatorInfoService gatorInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = gatorInfoService.queryPage(params);

        return R.ok().put("data", page);
    }

    /**
     * 合作者列表
     */
    @RequestMapping("/partners")
    public R partners(@RequestParam Map<String, Object> params){
        PageUtils page = gatorInfoService.partners(params);

        return R.ok().put("data", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info")
    public R info(@RequestParam Map<String, Object> params){
		GatorInfoEntity gatorInfo = gatorInfoService.getById(params.get("id").toString());

        return R.ok().put("data", gatorInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody GatorInfoEntity gatorInfo){
		gatorInfoService.save(gatorInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody GatorInfoEntity gatorInfo){
		gatorInfoService.updateById(gatorInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		gatorInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /*角色判断*/
    @RequestMapping("/role")
    public R role(@RequestParam("qbdz")  String qbdz){
        Long num = gatorInfoService.role(qbdz);
        return R.ok().put("data",num);
    }

    /*角色判断*/
    @RequestMapping("/ipify")
    public R ipify(@RequestParam("dz")  String ymdz){
        try {
            InetAddress address = InetAddress.getByName(ymdz);
            return R.ok().put("data",address.getHostAddress());
        } catch (UnknownHostException e) {
//            e.printStackTrace();
            return  R.ok().put("data",0);
        }
    }
}
