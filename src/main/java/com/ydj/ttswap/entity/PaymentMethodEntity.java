package com.ydj.ttswap.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 支付方式信息表
 * 
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
@Data
@TableName("otc_payment_method")
public class PaymentMethodEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long zffsid;
	/**
	 * 支付名称
	 */
	private String mc;
	/**
	 * 支付类别
0银行转账
1 收款码
2 现金交易
3其他账户
	 */
	private Integer lb;
	/**
	 * 图例
	 */
	private String tl;
	/**
	 * 状态\n0冻结\n1流通
	 */
	private Integer zt;
	/**
	 * 
	 */
	private Date cjsj;
	/**
	 * 
	 */
	private Date bgsj;

	private Integer ljsc;
}
