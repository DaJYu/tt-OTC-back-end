package com.ydj.ttswap.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 法币、支付方式、商品映射表
 * 
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
@Data
@TableName("otc_legal_currency_payment_method")
public class LegalCurrencyPaymentMethodEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 法币id
	 */
	@TableId
	private Long fbid;
	/**
	 * 支付方式id
	 */
	private Long zffsid;
	/**
	 * 商品id
	 */
	private Long spid;

}
