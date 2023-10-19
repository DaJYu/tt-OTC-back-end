package com.ydj.ttswap.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ydj.ttswap.entity.DepositHistoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 保证金历史记录信息表
 * 
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
@Mapper
public interface DepositHistoryMapper extends BaseMapper<DepositHistoryEntity> {

    @Select("SELECT a.*,b.name fbmc from otc_deposit_history a\n" +
            "   left join otc_legal_currency b on a.fbid=b.fbid\n" +
            " ${ew.customSqlSegment} ")
    IPage<DepositHistoryEntity> selectPages(IPage<DepositHistoryEntity> page, @Param("ew") QueryWrapper<DepositHistoryEntity> wrapper);
}
