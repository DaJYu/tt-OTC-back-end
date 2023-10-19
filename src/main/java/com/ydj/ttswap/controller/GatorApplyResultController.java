package com.ydj.ttswap.controller;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import com.ydj.ttswap.config.SaveFileConfig;
import com.ydj.ttswap.entity.GatorInfoEntity;
import com.ydj.ttswap.service.GatorInfoService;
import com.ydj.ttswap.utils.ServiceSaveFile;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ydj.ttswap.entity.GatorApplyResultEntity;
import com.ydj.ttswap.service.GatorApplyResultService;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.utils.R;



/**
 * 门户申请结果
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
@RestController
@RequestMapping("admin/gatorapply")
public class GatorApplyResultController {
    @Autowired
    private GatorApplyResultService gatorApplyResultService;
    @Autowired
    private GatorInfoService gatorInfoService;

    @Autowired
    SaveFileConfig saveFile;
    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = gatorApplyResultService.queryPage(params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info")
    public R info(@RequestParam("id") String dz){
		GatorApplyResultEntity gatorApplyResult = gatorApplyResultService.getResul(dz);

        return R.ok().put("gatorApplyResult", gatorApplyResult);
    }


    /*当前申请数*/
    @RequestMapping("/applyNum")
    public R applyNum(){
        Long num = gatorApplyResultService.getApplyNum();
        return R.ok().put("data",num);
    }
    /**
     *
     */
    @RequestMapping("/save")
    @Transactional(rollbackFor = Exception.class)
    public R save(GatorApplyResultEntity gatorApplyResult){

        Boolean pd = gatorApplyResultService.getPd(gatorApplyResult.getQbdz());
        if (pd){
            if (gatorApplyResult.getImg()!=null) {
                String fname=gatorApplyResult.getImg().getOriginalFilename();
                String fileName=gatorApplyResult.getQbdz()+fname.substring(fname.lastIndexOf("."), fname.length());
                String fileUrl=saveFile.getProfile();
                boolean fileUpload = ServiceSaveFile.singleFileUpload(gatorApplyResult.getImg(), fileName, fileUrl + "/icon-save/");
                if (fileUpload){
                    gatorApplyResult.setTb(fileName);
                } else {
                    return R.error("图片保存失败！");
                }
            }
            gatorApplyResultService.save(gatorApplyResult);
            GatorInfoEntity gatorInfoEntity = new GatorInfoEntity();
            BeanUtils.copyProperties(gatorApplyResult,gatorInfoEntity);
            gatorInfoService.save(gatorInfoEntity);
            return R.ok();
        }
        return R.error("申请已存在，请不要重复申请！");
    }

    /**
     *
     */
    @RequestMapping("/pd")
    public R pd(GatorApplyResultEntity gatorApplyResult) {
        Boolean pd = gatorApplyResultService.getPd(gatorApplyResult.getQbdz());
        return R.ok().put("pd",pd);
    }

    /**
     * 修改
     */
    @RequestMapping("/updates")
    @Transactional(rollbackFor = Exception.class)
    public R updates(@RequestBody GatorApplyResultEntity gatorApplyResult){
        if (gatorApplyResult.getImg()!=null) {
            String fname=gatorApplyResult.getImg().getOriginalFilename();
            String fileName=gatorApplyResult.getQbdz()+fname.substring(fname.lastIndexOf("."), fname.length());;
            String fileUrl=saveFile.getProfile();
            boolean fileUpload = ServiceSaveFile.singleFileUpload(gatorApplyResult.getImg(), fileName, fileUrl + "/icon-save/");
            if (fileUpload){
                gatorApplyResult.setTb(fileName);
            }
        }
        GatorApplyResultEntity entity1 = gatorApplyResultService.getId(gatorApplyResult.getQbdz());
        gatorApplyResult.setId(entity1.getId());

        GatorInfoEntity entity = gatorInfoService.getId(gatorApplyResult.getQbdz());
        GatorInfoEntity entitys = new GatorInfoEntity();
        BeanUtils.copyProperties(gatorApplyResult,entitys);
        entitys.setId(entity.getId());
        entitys.setGxsj(new Date());
        gatorApplyResultService.updateById(gatorApplyResult);
        gatorInfoService.updateById(entitys);

        return R.ok();
    }
    /**
     * 审核
     */
    @RequestMapping("/update")
    @Transactional(rollbackFor = Exception.class)
    public R update(@RequestBody GatorApplyResultEntity gatorApplyResult){
        gatorApplyResultService.updateById(gatorApplyResult);
        GatorInfoEntity entity = gatorInfoService.getId(gatorApplyResult.getQbdz());
        GatorInfoEntity entitys = new GatorInfoEntity();
        entitys.setId(entity.getId());
        entitys.setZt(gatorApplyResult.getZt());
        entitys.setGxsj(new Date());
        gatorInfoService.updateById(entitys);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		gatorApplyResultService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
