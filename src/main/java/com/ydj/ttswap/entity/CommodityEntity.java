package com.ydj.ttswap.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 商品
 * 
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-04-03 13:42:06
 */
@Data
@TableName("otc_commodity")
public class  CommodityEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long spid;
	/**
	 * 法币id
	 */
	private Long fbid;
	/**
	 * 法币名称
	 */
	private String fbmc;
	/**
	 * 币种合约地址
	 */
	private String bzhy;
	/**
	 * 币种名称
	 */
	private String bzmc;
	/**
	 * 币种外部链接
	 */
	private String xnblj;
	/**
	 * 币种单价
	 */
	private Double bzdj;
	/**
	 * 币种数量
	 */
	private Double bzsl;
	/**
	 * 单笔最高成交额限制
	 */
	private Double zgxe;
	/**
	 * 单笔最低成交额限制
	 */
	private Double zdxe;
	/**
	 * 截止时间
	 */
	@JsonFormat(timezone="GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date jzsj;
	/**
	 * 当前成交数量
	 */
	private Double cjsl;
	/**
	 * 当前被锁定数量
	 */
	private Double sdsl;
	/**
	 * 当前库存数量
	 */
	private Double kcsl;
	/**
	 * 商品出售类型
0 买入
1 卖出
	 */
	private Integer cslx;
	/**
	 * 状态
1 上架
0 下架
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
	 *支付方式id
	 */
//	private String zffsid;
	/*创建人，币商钱包地址*/
	private String cjr;
	private Integer ljsc;

	@TableField(exist = false)
	private Double ksyed;
//	@TableField(exist = false)
//	private String ksyed;
	@TableField(exist = false)
	private List<Long> zffsid;

}
