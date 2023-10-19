package com.ydj.ttswap.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ydj.ttswap.entity.MerchantLegalcurrencyEntity;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.vo.MerchantLegalcurrencyVo;
import com.ydj.ttswap.vo.ParameterVo;

import java.util.List;
import java.util.Map;

/**
 * 币商法币表
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-04-21 15:31:21
 */
public interface MerchantLegalcurrencyService extends IService<MerchantLegalcurrencyEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<MerchantLegalcurrencyVo> listSel(String id);

    Boolean selectOne(ParameterVo parameterVo);
}

