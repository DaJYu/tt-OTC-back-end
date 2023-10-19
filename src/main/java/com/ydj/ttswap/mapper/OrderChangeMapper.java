package com.ydj.ttswap.mapper;

import com.ydj.ttswap.entity.OrderChangeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单变更信息表
 * 
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
@Mapper
public interface OrderChangeMapper extends BaseMapper<OrderChangeEntity> {
	
}
