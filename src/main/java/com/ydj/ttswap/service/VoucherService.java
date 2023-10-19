package com.ydj.ttswap.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.entity.VoucherEntity;

import java.util.List;
import java.util.Map;

/**
 * 交易凭据
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
public interface VoucherService extends IService<VoucherEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<VoucherEntity> lists(Map<String,Object> params);
}

