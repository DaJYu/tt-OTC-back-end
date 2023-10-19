package com.ydj.ttswap.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.ydj.ttswap.config.SaveFileConfig;
import com.ydj.ttswap.utils.ServiceSaveFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ydj.ttswap.entity.CoinDealerApplyEntity;
import com.ydj.ttswap.service.CoinDealerApplyService;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.utils.R;



/**
 * 币商申请表
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
@RestController
@RequestMapping("otc/coindealerapply")
public class CoinDealerApplyController {
    @Autowired
    private CoinDealerApplyService coinDealerApplyService;

    @Autowired
    SaveFileConfig saveFile;
    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = coinDealerApplyService.getApplyList(params);

        return R.ok().put("data", page);
    }
    /**
     * 列表
     */
    @RequestMapping("/merchant/list")
    public R lists(@RequestParam Map<String, Object> params){
        PageUtils page = coinDealerApplyService.getApplyLists(params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info")
    public R info(@RequestParam("id") Integer id){
        List list = coinDealerApplyService.selInfo(id);

        return R.ok().put("data", list);
    }
    /**
     * 信息
     */
    @RequestMapping("/infos")
    public R infos(@RequestParam("bsid") String id){
        CoinDealerApplyEntity coin = coinDealerApplyService.setInfo(id);

        return R.ok().put("data", coin);
    }

    /*当前申请数*/
    @RequestMapping("/applyNum")
    public R applyNum(){
        Long num = coinDealerApplyService.getApplyNum();
        return R.ok().put("data",num);
    }
    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(CoinDealerApplyEntity coinDealerApply){
        Boolean pd = coinDealerApplyService.getPd(coinDealerApply.getBsqb());

        if (pd && coinDealerApply.getImg()!=null) {
            String fname=coinDealerApply.getImg().getOriginalFilename();
//        System.out.print(file.getOriginalFilename());
            String fileName=coinDealerApply.getBsqb()+fname.substring(fname.lastIndexOf("."), fname.length());
            String fileUrl=saveFile.getProfile();
            boolean fileUpload = ServiceSaveFile.singleFileUpload(coinDealerApply.getImg(), fileName, fileUrl + "/icon-save/");
            if (fileUpload){
                coinDealerApply.setDptp(fileName);
                coinDealerApply.setSqlb(1);
                coinDealerApplyService.save(coinDealerApply);
                return R.ok();
            } else {
                return R.error();
            }
        } else {
            return R.error("申请已存在，请不要重复申请！");
        }
    }

    /**
     * 审核
     */
    @RequestMapping("/update")
    @Transactional(rollbackFor = Exception.class)
    public R update(@RequestBody CoinDealerApplyEntity coinDealerApply){
        try {
            coinDealerApplyService.updateApply(coinDealerApply);
            coinDealerApplyService.updateById(coinDealerApply);
            return R.ok();
        } catch (Exception e){
            return R.error();
        }
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		coinDealerApplyService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
