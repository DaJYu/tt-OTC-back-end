package com.ydj.ttswap.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ydj.ttswap.entity.DepositEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ydj.ttswap.vo.DepositRecordVo;
import com.ydj.ttswap.vo.DepositVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 保证金信息表
 * 
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
@Mapper
public interface DepositMapper extends BaseMapper<DepositEntity> {

    @Select("SELECT * from (SELECT a.*,b.qc bsqc,b.jc bsjc,c.name fbmc FROM otc_deposit a\n" +
            "left join otc_coin_dealer b on a.bsid=b.bsqb\n" +
            "left join otc_legal_currency c on a.fbid=c.fbid)a " +
            " ${ew.customSqlSegment} ")
    IPage<DepositVo> selectPages(IPage<DepositVo> page, @Param("ew") QueryWrapper<DepositVo> wrapper);


    @Select("SELECT * from (SELECT a.*,b.name fbmc FROM otc_deposit_history a\n" +
            "left join otc_legal_currency b on a.fbid=b.fbid)a " +
            " ${ew.customSqlSegment} ")
    IPage<DepositRecordVo> recordSelectPages(IPage<DepositRecordVo> page, @Param("ew") QueryWrapper<DepositRecordVo> wrapper);
}
