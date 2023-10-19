package com.ydj.ttswap.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.entity.DepositEntity;

import java.util.Map;

/**
 * 保证金信息表
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
public interface DepositService extends IService<DepositEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPage(String js, Map<String,Object> params);

    PageUtils queryPages(Map<String,Object> params);
}

