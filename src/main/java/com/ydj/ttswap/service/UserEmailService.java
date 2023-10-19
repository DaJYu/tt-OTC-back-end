package com.ydj.ttswap.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ydj.ttswap.entity.UserEmailEntity;
import com.ydj.ttswap.utils.PageUtils;

import java.util.Map;

/**
 * 用户邮箱
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-08-14 18:40:28
 */
public interface UserEmailService extends IService<UserEmailEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

