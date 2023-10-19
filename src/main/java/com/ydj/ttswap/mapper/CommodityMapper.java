package com.ydj.ttswap.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ydj.ttswap.entity.CommodityEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ydj.ttswap.vo.OtcListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 商品
 * 
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-04-03 13:42:06
 */
@Mapper
public interface CommodityMapper extends BaseMapper<CommodityEntity> {

    @Select("SELECT a.*,b.ksyed FROM (select * from otc_commodity where ljsc=0) a\n" +
            "left join otc_deposit b on a.cjr=b.bsid and a.fbid=b.fbid\n" +
            " ${ew.customSqlSegment} ")
    IPage<CommodityEntity> page(IPage<CommodityEntity> page, @Param("ew") QueryWrapper<CommodityEntity> wrapper);

    @Select("SELECT a.* from(SELECT a.*,b.jc,b.dptp,b.qc,c.fh fbfh,c.dm fbdm,d.ksyed,GROUP_CONCAT(n.mc SEPARATOR ',')zffs,ifnull(f.xdl,0)xdl,ifnull(f.wcl,0)wcl FROM (select * from otc_commodity where zt=1 and ljsc=0) a\n" +
            "left join otc_coin_dealer b on a.cjr=b.bsqb\n" +
            "left join otc_legal_currency c on a.fbid=c.fbid\n" +
            "left join otc_deposit d on a.cjr=d.bsid and a.fbid=d.fbid\n" +
            "left join otc_legal_currency_payment_method e on a.spid=e.spid\n" +
            "left join otc_payment_method n on e.zffsid=n.zffsid\n" +
            "left join (select a.spid,sum(if(a.ddzt=1001,a.z,0)) xdl,sum(if(a.ddzt=1005,a.z,0)) wcl from (SELECT spid,ddzt,count(*)z from otc_order_change where ddzt in (1001,1005) group by spid,ddzt)a) f on a.spid=f.spid\n" +
            "group by spid)a\n" +
            "left join otc_legal_currency_payment_method b on a.spid=b.spid\n" +
            " ${ew.customSqlSegment} ")
    IPage<OtcListVo> pages(IPage<OtcListVo> page, @Param("ew") QueryWrapper<OtcListVo> wrapper);
}
