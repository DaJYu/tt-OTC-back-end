package com.ydj.ttswap.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ydj.ttswap.entity.PaymentAccountEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ydj.ttswap.entity.PaymentMethodEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 收款账户配置信息表
 * 
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
@Mapper
public interface PaymentAccountMapper extends BaseMapper<PaymentAccountEntity> {

    @Select("SELECT a.zffsid,b.mc,b.lb FROM (SELECT * FROM otc_payment_account where ljsc=0 and cjr=#{bsqb} and zt=1 group by zffsid) a\n" +
            "left join (select * from otc_payment_method where ljsc=0) b on a.zffsid=b.zffsid\n")
    List<PaymentMethodEntity> selectLists(@Param("bsqb") String bsqb);

    @Select("SELECT a.zffsid,b.mc,b.lb FROM (SELECT * FROM otc_legal_currency_payment_method where spid=#{spid} group by zffsid) a\n" +
            "left join (select * from otc_payment_method where ljsc=0) b on a.zffsid=b.zffsid\n")
    List<PaymentMethodEntity> selectListUser(@Param("spid") String spid);
}
