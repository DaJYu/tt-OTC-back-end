package com.ydj.ttswap.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ydj.ttswap.entity.GatorMarketorEntity;
import com.ydj.ttswap.utils.PageUtils;

import java.util.Map;

/**
 * 门户管理员
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-08-03 13:21:12
 */
public interface GatorMarketorService extends IService<GatorMarketorEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

