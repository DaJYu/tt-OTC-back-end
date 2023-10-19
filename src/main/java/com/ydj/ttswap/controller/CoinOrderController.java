package com.ydj.ttswap.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.ydj.ttswap.config.SaveFileConfig;
import com.ydj.ttswap.utils.ServiceSaveFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ydj.ttswap.entity.CoinOrderEntity;
import com.ydj.ttswap.service.CoinOrderService;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.utils.R;



/**
 * 虚拟币、物品图标对应表
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
@RestController
@RequestMapping("otc/coinorder")
public class CoinOrderController {
    @Autowired
    private CoinOrderService coinOrderService;

    @Autowired
    SaveFileConfig saveFile;
    /**
     * 列表
     */
    @RequestMapping("/{lx}/list")
    public R list(@PathVariable("lx") String lx,@RequestParam Map<String, Object> params){
        PageUtils page = coinOrderService.queryPage(lx,params);
        return R.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/portal/{lx}/list")
    public R lists(@PathVariable("lx") String lx,@RequestParam Map<String, Object> params){
        PageUtils page = coinOrderService.queryPages(lx,params);
        return R.ok().put("data", page);
    }
    /**
     * 列表
     */
    @RequestMapping("/merchant/listSel")
    public R listSel(){
        List<CoinOrderEntity> page = coinOrderService.lists();

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		CoinOrderEntity coinOrder = coinOrderService.getById(id);

        return R.ok().put("coinOrder", coinOrder);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(CoinOrderEntity coinOrder){
        Boolean pd = coinOrderService.getPd(coinOrder);
        if (pd) {
            if (coinOrder.getImg()!=null){
                String fname=coinOrder.getImg().getOriginalFilename();
                String fileName=coinOrder.getHydz()+fname.substring(fname.lastIndexOf("."), fname.length());;
                String fileUrl=saveFile.getProfile();
                boolean fileUpload = ServiceSaveFile.singleFileUpload(coinOrder.getImg(), fileName, fileUrl + "/icon-save/");
                if (fileUpload){
                    coinOrder.setTb(fileName);
                    coinOrderService.save(coinOrder);
                    return R.ok();
                } else {
                    return R.error();
                }
            } else {
                return R.error("图标不能为空");
            }
        } else {
            return R.error("名称或合约地址已存在");
        }
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CoinOrderEntity coinOrder){
		coinOrderService.updateById(coinOrder);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		coinOrderService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
