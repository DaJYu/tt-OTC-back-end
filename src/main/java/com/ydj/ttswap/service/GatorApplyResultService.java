package com.ydj.ttswap.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.entity.GatorApplyResultEntity;

import java.util.Map;

/**
 * 门户申请结果
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
public interface GatorApplyResultService extends IService<GatorApplyResultEntity> {

    PageUtils queryPage(Map<String, Object> params);

    GatorApplyResultEntity getResul(String dz);

    Long getApplyNum();

    Boolean getPd(String qbdz);

    GatorApplyResultEntity getId(String qbdz);
}

