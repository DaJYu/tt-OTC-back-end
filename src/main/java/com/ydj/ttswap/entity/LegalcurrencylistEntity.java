package com.ydj.ttswap.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * VIEW
 * 
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-08-03 13:53:25
 */
@Data
@TableName("legalcurrencylist")
public class LegalcurrencylistEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long fbid;
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
	 * 状态\n0冻结\n1流通
	 */
	private Integer zt;
	/**
	 * 是否收取手续费\\n0不收\\n1收
	 */
	private Integer sfsxf;
	/**
	 * 费率
	 */
	private Double fl;
	/**
	 * 
	 */
	private Date cjsj;
	/**
	 * 
	 */
	private String zffs;
	/**
	 * 
	 */
	private String zffsid;
	/**
	 * 
	 */
	private String zffslb;

}
