package com.ydj.ttswap.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ydj.ttswap.entity.CoinDealerApplyEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ydj.ttswap.vo.CoinDealerExamineVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 币商申请表
 * 
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
@Mapper
public interface CoinDealerApplyMapper extends BaseMapper<CoinDealerApplyEntity> {

    @Select("SELECT a.*,b.*,d.zhmc,d.khmc,d.zhm,d.zflx,d.skm,e.name fbmc,g.mc zffs FROM (SELECT * FROM otc_coin_dealer_apply" +
            " where sqid=#{id}) a\n" +
            "    left join otc_coin_dealer b on a.bsqb=b.bsqb\n" +
//            "    left join otc_deposit c on a.bsqb=c.bsid and a.fbid=c.fbid\n" +
            "    left join otc_payment_account d on a.skzhid=d.zhid\n" +
            "    left join otc_legal_currency e on a.fbid=e.fbid\n" +
            "    left join otc_payment_method g on d.zffsid=g.zffsid;")
    List<CoinDealerExamineVo> findByJoin(@Param("id") Integer id);

    @Select("SELECT a.*,b.name fbmc from otc_coin_dealer_apply a\n" +
            "   left join otc_legal_currency b on a.fbid=b.fbid\n" +
            " ${ew.customSqlSegment} ")
    IPage<CoinDealerApplyEntity> selectPages(IPage<CoinDealerApplyEntity> page, @Param("ew") QueryWrapper<CoinDealerApplyEntity> wrapper);
}
