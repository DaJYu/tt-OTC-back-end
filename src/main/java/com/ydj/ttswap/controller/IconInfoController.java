package com.ydj.ttswap.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ydj.ttswap.entity.IconInfoEntity;
import com.ydj.ttswap.service.IconInfoService;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.utils.R;
import org.springframework.web.multipart.MultipartFile;


/**
 * 图标信息表
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
@RestController
@RequestMapping("otc/iconinfo")
public class IconInfoController {
    @Autowired
    private IconInfoService iconInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = iconInfoService.queryPage(params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info")
    public R info(@RequestParam("id") String id){
		IconInfoEntity iconInfo = iconInfoService.getById(id);

        return R.ok().put("data", iconInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/saveIcon")
    public R saveIcon(@RequestParam("img") MultipartFile file, @RequestParam("id") String id, @RequestParam("fl") int fl){
        Boolean aBoolean= iconInfoService.saveIcon(file,id,fl);
        if (aBoolean){
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody IconInfoEntity iconInfo){
		iconInfoService.updateById(iconInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] ids){
		iconInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
