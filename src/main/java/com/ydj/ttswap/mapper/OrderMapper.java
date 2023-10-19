package com.ydj.ttswap.mapper;

import com.ydj.ttswap.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单信息表
 * 
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderEntity> {
	
}
