package com.ydj.ttswap.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ydj.ttswap.entity.GatorOtcIncomeDrawRecordEntity;
import com.ydj.ttswap.utils.PageUtils;

import java.util.Map;

/**
 * 门户OTC收益提取记录表
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-07-19 15:01:45
 */
public interface GatorOtcIncomeDrawRecordService extends IService<GatorOtcIncomeDrawRecordEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

