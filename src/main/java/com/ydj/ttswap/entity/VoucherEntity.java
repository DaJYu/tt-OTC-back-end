package com.ydj.ttswap.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 交易凭据
 * 
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
@Data
@TableName("otc_voucher")
public class VoucherEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 订单id
	 */
	@TableId
	private Long ddid;
	/**
	 * 状态
	 */
	private Integer ddzt;
	/**
	 * 角色\n1 币商\n2 用户
	 */
	private Integer js;
	/**
	 * 图片凭据
	 */
	private String tppz;
	/**
	 * 视频凭据
	 */
	private String sppz;
	/**
	 * 
	 */
	private Date cjsj;

	private Integer ljsc;
}
