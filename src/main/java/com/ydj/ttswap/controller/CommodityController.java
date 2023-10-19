package com.ydj.ttswap.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ydj.ttswap.entity.LegalCurrencyPaymentMethodEntity;
import com.ydj.ttswap.service.LegalCurrencyPaymentMethodService;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ydj.ttswap.entity.CommodityEntity;
import com.ydj.ttswap.service.CommodityService;



/**
 * 商品
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-04-03 13:42:06
 */
@RestController
@RequestMapping("otc/commodity")
public class CommodityController {
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private LegalCurrencyPaymentMethodService legalCurrencyPaymentMethodService;

    /**
     * otc列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = commodityService.queryPage(params);

        return R.ok().put("data", page);
    }

    /**
     * 币商店铺商品列表
     */
    @RequestMapping("/merchant/list")
    public R lists(@RequestParam Map<String, Object> params){
        PageUtils page = commodityService.queryPages(params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		CommodityEntity commodity = commodityService.getById(id);

        return R.ok().put("commodity", commodity);
    }

    /**
     * 保存
     */
    @RequestMapping("/merchant/save")
    @Transactional(rollbackFor = Exception.class)
    public R save(@RequestBody CommodityEntity commodity){
        commodity.setKcsl(commodity.getBzsl());
		commodityService.save(commodity);
        List<LegalCurrencyPaymentMethodEntity> list= commodity.getZffsid().stream().map(i->{
            LegalCurrencyPaymentMethodEntity entity = new LegalCurrencyPaymentMethodEntity();
            entity.setSpid(commodity.getSpid());
            entity.setZffsid(i.longValue());
            return entity;
        }).collect(Collectors.toList());
//        System.out.println(list);
        legalCurrencyPaymentMethodService.saveBatch(list);

        return R.ok();
    }

    /**
     * 上架
     */
    @RequestMapping("/merchant/grounding")
    public R grounding(@RequestBody CommodityEntity commodity){
        CommodityEntity entity=commodityService.getById(commodity);

//        System.out.println(entity);
        long jzsj = entity.getJzsj().getTime();
        long gxsj = new Date().getTime();

        if (jzsj>gxsj){
            commodity.setZt(1);
            commodity.setGxsj(new Date());
            commodityService.updateById(commodity);
            return R.ok();
        } else {
            return R.error("已过截止时间，无法上架，请重新创建商品！");
        }
    }
    /**
     * 下架
     */
    @RequestMapping("/merchant/withdraw")
    public R withdraw(@RequestBody CommodityEntity commodity){
        commodity.setZt(0);
        commodity.setGxsj(new Date());
        commodityService.updateById(commodity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/merchant/update")
    public R update(@RequestBody CommodityEntity commodity){
        commodityService.updateById(commodity);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/merchant/delete")
    public R delete(@RequestBody CommodityEntity ids){
		commodityService.removeById(ids);

        return R.ok();
    }

}
