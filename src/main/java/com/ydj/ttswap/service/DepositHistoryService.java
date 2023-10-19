package com.ydj.ttswap.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.entity.DepositHistoryEntity;

import java.util.Map;

/**
 * 保证金历史记录信息表
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
public interface DepositHistoryService extends IService<DepositHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

