package com.ydj.ttswap.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.entity.CoinDealerApplyEntity;

import java.util.List;
import java.util.Map;

/**
 * 币商申请表
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
public interface CoinDealerApplyService extends IService<CoinDealerApplyEntity> {

    PageUtils getApplyList(Map<String, Object> params);

    Long getApplyNum();

    List selInfo(Integer id);

    void updateApply(CoinDealerApplyEntity coinDealerApply);

    Boolean getPd(String bsqb);

    CoinDealerApplyEntity setInfo(String id);

    PageUtils getApplyLists(Map<String,Object> params);

//    PageUtils getApplyList(Map<String,Object> params);
}

