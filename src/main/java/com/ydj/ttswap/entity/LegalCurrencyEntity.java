package com.ydj.ttswap.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 法币信息表
 * 
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
@Data
@TableName("otc_legal_currency")
public class LegalCurrencyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long fbid;
	/**
	 * 图标
	 */
	private String tb;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 符号
	 */
	private String fh;
	/**
	 * 代码
	 */
	private String dm;
	/**
	 * 保证金额度
	 */
	private Double bzjed;
	/**
	 * 状态
	 0冻结
	 1流通
	 */
	private Integer zt;
	/**
	 * 手续费费率
	 */
	private Double fl;
	/**
	 * 是否收取手续费\\n0不收\\n1收
	 */
	private Integer sfsxf;
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
