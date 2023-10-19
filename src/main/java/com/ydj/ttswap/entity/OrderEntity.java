package com.ydj.ttswap.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 订单信息表
 * 
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
@Data
@TableName("otc_order")
public class OrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long ddid;
	/**
	 * 商品id
	 */
	private Long spid;
	private Long fbid;
	/**
	 * 币商地址
	 */
	private String bsdz;
	/**
	 * 用户地址
	 */
	private String yhdz;
	/**
	 * 状态
	 */
	private Integer zt;
	/**
	 * 币种名称
	 */
	private String bzmc;
	/**
	 * 币种数量
	 */
	private Double sl;
	/**
	 * 法币名称
	 */
	private String fbmc;
	/**
	 * 金额
	 */
	private Double je;
	/**
	 * 订单类型\\n0 买入\\n1 卖出
	 */
	private Integer ddlx;
	/**
	 * 审核状态\n0 待审核\n1 审核完成
	 */
	private Integer shzt;
	/**
	 * 交易时间
	 */
	private Date cjsj;
	/**
	 * 状态更新时间
	 */
	private Date gxsj;
	/*最大限制时间*/
	@TableField(updateStrategy=FieldStrategy.IGNORED)
	private Date zdsx;
	private Integer ljsc;
/*币种合约*/
	private String bzhy;
	private String shyj;
	private String skr;
	private String dkr;
	private Long skzh;
	private Long dkzh;
	private Double sxf;
	private Double fl;
	/*哈希码，支付交易查询凭证*/
	private String hxz;
	@TableField(exist = false)
	private Integer shid;

}
