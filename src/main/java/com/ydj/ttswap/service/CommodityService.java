package com.ydj.ttswap.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ydj.ttswap.entity.CommodityEntity;
import com.ydj.ttswap.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 商品
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-04-03 13:42:06
 */
public interface CommodityService extends IService<CommodityEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPages(Map<String,Object> params);

    List<CommodityEntity> statusList();

    void productDeadline(CommodityEntity i);
}

