package com.ydj.ttswap.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.entity.CoinOrderEntity;

import java.util.List;
import java.util.Map;

/**
 * 虚拟币、物品图标对应表
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
public interface CoinOrderService extends IService<CoinOrderEntity> {

    PageUtils queryPage(String lx, Map<String, Object> params);

    List<CoinOrderEntity> lists();

    PageUtils queryPages(String lx, Map<String,Object> params);

    Boolean getPd(CoinOrderEntity coinOrder);
}

