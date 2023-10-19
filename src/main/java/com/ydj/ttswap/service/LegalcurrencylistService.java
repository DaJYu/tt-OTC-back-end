package com.ydj.ttswap.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ydj.ttswap.entity.LegalcurrencylistEntity;
import com.ydj.ttswap.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * VIEW
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-04-05 17:13:42
 */
public interface LegalcurrencylistService extends IService<LegalcurrencylistEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<LegalcurrencylistEntity> listSel();

    List<LegalcurrencylistEntity> bsLists(Map<String,Object> params);
}

