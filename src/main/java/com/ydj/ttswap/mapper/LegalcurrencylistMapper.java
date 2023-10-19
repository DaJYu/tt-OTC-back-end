package com.ydj.ttswap.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ydj.ttswap.entity.LegalcurrencylistEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * VIEW
 * 
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-04-05 17:13:42
 */
@Mapper
public interface LegalcurrencylistMapper extends BaseMapper<LegalcurrencylistEntity> {

    @Select("SELECT * FROM legalcurrencylist a\n" +
            "right join (select * from otc_merchant_legalcurrency where ljsc=0 and zt=1) b on a.fbid=b.fbid\n " +
            " ${ew.customSqlSegment} ")
    List<LegalcurrencylistEntity> selectLists(@Param("ew") QueryWrapper<LegalcurrencylistEntity> wrapper);
}
