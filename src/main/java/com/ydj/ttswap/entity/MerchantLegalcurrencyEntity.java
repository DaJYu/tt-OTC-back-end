package com.ydj.ttswap.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 币商法币表
 * 
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-04-21 15:31:21
 */
@Data
@TableName("otc_merchant_legalcurrency")
public class MerchantLegalcurrencyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Long fbid;
	/**
	 * 
	 */
	private Long bzjid;
	/**
	 * 
	 */
	private Integer zt;
	/**
	 * 
	 */
	private Date cjsj;
	/**
	 * 
	 */
	private Date gxsj;
	/**
	 * 
	 */
	private Integer ljsc;
	private String bsid;
//	private String zffsid;

}
