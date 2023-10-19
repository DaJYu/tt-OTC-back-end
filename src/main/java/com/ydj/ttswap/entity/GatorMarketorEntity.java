package com.ydj.ttswap.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 门户管理员
 * 
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-08-03 13:21:12
 */
@Data
@TableName("gator_marketor")
public class GatorMarketorEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private String zh;
	/**
	 * 
	 */
	private String mc;
	/**
	 * 
	 */
	private Integer ljsc;
	/**
	 * 
	 */
	private Date cjsj;

}
