package com.ydj.ttswap.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.entity.CoinDealerEntity;

import java.util.Map;

/**
 * 币商信息表
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
public interface CoinDealerService extends IService<CoinDealerEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void getStateChanges(Map<String,Object> params);

    CoinDealerEntity getBsqb(String bsqb);

    Long role(String qbdz);
}

