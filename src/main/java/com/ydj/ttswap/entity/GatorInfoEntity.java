package com.ydj.ttswap.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 门户信息表
 * 
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-06-11 21:51:38
 */
@Data
@TableName("gator_info")
public class GatorInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 简称
	 */
	private String jc;
	/**
	 * 全称
	 */
	private String qc;
	/**
	 * 国家
	 */
	private String gj;
	/**
	 * 官网地址
	 */
	private String gwdz;
	/**
	 * 官网IP
	 */
	private String ip;
	/**
	 * 钱包地址
	 */
	private String qbdz;
	/**
	 * 图标名称
	 */
	private String tb;
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

}
