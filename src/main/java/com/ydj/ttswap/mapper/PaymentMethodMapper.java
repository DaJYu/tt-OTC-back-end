package com.ydj.ttswap.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ydj.ttswap.entity.PaymentMethodEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.vo.PaymentAccountVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 支付方式信息表
 * 
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
@Mapper
public interface PaymentMethodMapper extends BaseMapper<PaymentMethodEntity> {

    @Select("SELECT a.*,b.mc zffs,c.name fbmc FROM otc_payment_account a\n" +
            "left join otc_payment_method b on a.zffsid=b.zffsid\n" +
            "left join otc_legal_currency c on a.fbid=c.fbid\n" +
            " ${ew.customSqlSegment} ")
    List<PaymentAccountVo> findByJoin(@Param("ew") QueryWrapper<PaymentAccountVo> wrapper);
}
