package com.ydj.ttswap.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 图标信息表
 * 
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
@Data
@TableName("icon_info")
public class IconInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String tbid;
	/**
	 * 图片名称
	 */
	private String mc;
	/**
	 * 分类\n0 虚拟币\n1 物品\n2 logo
	 */
	private Integer fl;
	/**
	 * 
	 */
	private Date cjsj;
	/**
	 * 
	 */
	private Date xgsj;

}
