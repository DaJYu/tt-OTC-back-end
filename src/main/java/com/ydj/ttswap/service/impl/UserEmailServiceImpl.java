package com.ydj.ttswap.service.impl;

import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ydj.ttswap.mapper.UserEmailMapper;
import com.ydj.ttswap.entity.UserEmailEntity;
import com.ydj.ttswap.service.UserEmailService;


@Service("userEmailService")
public class UserEmailServiceImpl extends ServiceImpl<UserEmailMapper, UserEmailEntity> implements UserEmailService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserEmailEntity> page = this.page(
                new Query<UserEmailEntity>().getPage(params),
                new QueryWrapper<UserEmailEntity>()
        );

        return new PageUtils(page);
    }

}