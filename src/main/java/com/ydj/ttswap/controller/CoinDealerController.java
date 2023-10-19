package com.ydj.ttswap.controller;

import com.ydj.ttswap.config.SaveFileConfig;
import com.ydj.ttswap.entity.CoinDealerApplyEntity;
import com.ydj.ttswap.entity.CoinDealerEntity;
import com.ydj.ttswap.service.CoinDealerApplyService;
import com.ydj.ttswap.service.CoinDealerService;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.utils.R;
import com.ydj.ttswap.utils.ServiceSaveFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 *币商管理
 */
@RestController
@RequestMapping("otc/merchant")
public class CoinDealerController {

    @Autowired
    private CoinDealerService coinDealerService;
    @Autowired
    private CoinDealerApplyService coinDealerApplyService;

    @Autowired
    SaveFileConfig saveFile;

    /*币商列表*/
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = coinDealerService.queryPage(params);
        return R.ok().put("data",page);
    }
    /**
     * 信息
     */
    @RequestMapping("/info")
    public R info(@RequestParam("bsid")  String bsqb){
        CoinDealerEntity coinDealer = coinDealerService.getBsqb(bsqb);

        return R.ok().put("data", coinDealer);
    }
    /*币商状态变更*/
    @RequestMapping("/stateChanges")
    public R stateChanges(@RequestParam Map<String, Object> params){
        coinDealerService.getStateChanges(params);
        return R.ok();
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @Transactional(rollbackFor = Exception.class)
    public R save(CoinDealerEntity coinDealer){


        String fname=coinDealer.getImg().getOriginalFilename();
//        System.out.print(file.getOriginalFilename());
        String fileName=coinDealer.getBsqb()+fname.substring(fname.lastIndexOf("."), fname.length());;
        String fileUrl=saveFile.getProfile();
        boolean fileUpload = ServiceSaveFile.singleFileUpload(coinDealer.getImg(), fileName, fileUrl + "/icon-save/");
        if (fileUpload){
            coinDealer.setDptp(fileName);
            coinDealer.setZt(2);
            coinDealerService.save(coinDealer);

            CoinDealerApplyEntity entity = new CoinDealerApplyEntity();
            entity.setBsid(coinDealer.getBsid());
            entity.setBsqb(coinDealer.getBsqb());
            entity.setSqlb(1);
            coinDealerApplyService.save(entity);
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(CoinDealerEntity coinDealer) {
        if (coinDealer.getImg() != null) {
            String fname = coinDealer.getImg().getOriginalFilename();
            String fileName = coinDealer.getBsqb() + fname.substring(fname.lastIndexOf("."), fname.length());
            ;
            String fileUrl = saveFile.getProfile();
            ServiceSaveFile.deleteFile(fileUrl + "/icon-save/" + coinDealer.getDptp());
            boolean fileUpload = ServiceSaveFile.singleFileUpload(coinDealer.getImg(), fileName, fileUrl + "/icon-save/");
            if (fileUpload) {
                coinDealer.setDptp(fileName);
            }
        }
        coinDealer.setGxsj(new Date());
        coinDealerService.updateById(coinDealer);

        return R.ok();
    }

    /**
     * 状态变更
     */
    @RequestMapping("/statusChange")
    public R statusChange(@RequestBody CoinDealerEntity coinDealer) {
        coinDealer.setGxsj(new Date());
        coinDealerService.updateById(coinDealer);

        return R.ok();
    }
    /**
     * 删除
     */
    @RequestMapping("/delete/{id}")
    public R delete(@PathVariable("id") Integer ids){
        coinDealerService.removeById(ids);

        return R.ok();
    }

    /*角色判断*/
    @RequestMapping("/role")
    public R role(@RequestParam("qbdz")  String qbdz){
        Long num = coinDealerService.role(qbdz);
        return R.ok().put("data",num);
    }
}
