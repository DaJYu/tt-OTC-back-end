package com.ydj.ttswap.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ydj.ttswap.entity.MerchantLegalcurrencyEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ydj.ttswap.vo.MerchantLegalcurrencyVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 币商法币表
 * 
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-04-21 15:31:21
 */
@Mapper
public interface MerchantLegalcurrencyMapper extends BaseMapper<MerchantLegalcurrencyEntity> {

    @Select("SELECT * from (SELECT a.*,b.qc bsqc,b.jc bsjc,c.name fbmc,c.fh fbfh,c.dm fbdm,c.sfsxf,c.fl,n.bzj,n.ksyed FROM otc_merchant_legalcurrency a\n" +
            "left join otc_deposit n on a.bzjid=n.bzjid\n" +
            "left join otc_coin_dealer b on a.bsid=b.bsqb\n" +
            "left join otc_legal_currency c on a.fbid=c.fbid)a " +
            " ${ew.customSqlSegment} ")
    IPage<MerchantLegalcurrencyVo> selectPages(IPage<MerchantLegalcurrencyVo> page, @Param("ew") QueryWrapper<MerchantLegalcurrencyVo> wrapper);

    @Select("SELECT a.fbid,a.fbmc from (SELECT a.*,b.name fbmc FROM otc_merchant_legalcurrency a\n" +
            "left join otc_legal_currency b on a.fbid=b.fbid)a " +
            " ${ew.customSqlSegment} ")
    List<MerchantLegalcurrencyVo> selectLists(@Param("ew") QueryWrapper<MerchantLegalcurrencyVo> wrapper);
}
