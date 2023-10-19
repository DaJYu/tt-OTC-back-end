package com.ydj.ttswap.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ydj.ttswap.entity.LegalCurrencyEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.vo.LegalCurrencyVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * 法币信息表
 * 
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
@Mapper
public interface LegalCurrencyMapper extends BaseMapper<LegalCurrencyEntity> {

    @Select("SELECT a.id,a.name,a.fh,a.dm,a.bzjed,a.zt,a.cjsj,cast(GROUP_CONCAT(c.mc) as char) zffs,cast(GROUP_CONCAT(c.id) as char) zffsID FROM otc_legal_currency a\n" +
            " left join otc_legal_currency_payment_method b on a.id=b.fbid\n" +
            " left join (SELECT * FROM otc_payment_method where ljsc=0 and zt=1) c on b.zffsid=c.id\n" +
            " where a.ljsc=0\n" +
            " group by a.id;")
    PageUtils findByJoin(Map<String,Object> params, QueryWrapper<LegalCurrencyVo> wrapper);
}
