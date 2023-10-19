package com.ydj.ttswap.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ydj.ttswap.entity.GatorInfoEntity;
import com.ydj.ttswap.utils.PageUtils;

import java.util.Map;

/**
 * 门户信息表
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-06-11 21:51:38
 */
public interface GatorInfoService extends IService<GatorInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    GatorInfoEntity getId(String qbdz);

    PageUtils partners(Map<String,Object> params);

    Long role(String qbdz);
}

