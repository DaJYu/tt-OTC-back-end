package com.ydj.ttswap.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ydj.ttswap.entity.LegalcurrencylistEntity;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.entity.LegalCurrencyEntity;

import java.util.List;
import java.util.Map;

/**
 * 法币信息表
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
public interface LegalCurrencyService extends IService<LegalCurrencyEntity> {

//    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPages(Map<String,Object> params);

    List<LegalCurrencyEntity> queryPage();

    List<LegalcurrencylistEntity> listSel();
}

