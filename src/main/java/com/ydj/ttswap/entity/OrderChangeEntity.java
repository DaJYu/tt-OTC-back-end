package com.ydj.ttswap.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 订单变更信息表
 * 
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
@Data
@TableName("otc_order_change")
public class OrderChangeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 订单id
	 */
	@TableId
	private Long ddid;
	private Long spid;
	/**
	 * 订单状态
	 */
	private Integer ddzt;
	/**
	 * 角色
1 币商
2 用户
	 */
	private Integer js;
	/**
	 * 地址
	 */
	private String dz;
	/**
	 * 状态更新时间
	 */
	private Date ztsj;
	/**
	 * 申述时间
	 */
	private Date sfsj;
	/**
	 * 申诉原因
	 */
	private String sfyy;
	/*状态变更原因*/
	private String ztbgyy;
	/**
	 * 创建时间
	 */
	private Date cjsj;
	private Integer ljsc;

}
