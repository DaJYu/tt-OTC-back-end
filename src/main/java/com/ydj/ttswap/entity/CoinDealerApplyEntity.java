package com.ydj.ttswap.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * 币商申请表
 * 
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
@Data
@TableName("otc_coin_dealer_apply")
public class CoinDealerApplyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long sqid;

	private Long bsid;
	private Long bzjid;
	/**
	 * 币商钱包地址
	 */
	private String bsqb;
	/**
	 * 申请类型
1 新增币商
2 新增法币
3 续交保证金
4 退保证金
	 */
	private Integer sqlb;
	/**
	 * 法币id
	 */
	private Long fbid;
	/**
	 * 支付人
	 */
	private String zfr;
	/**
	 * 收款账户id
	 */
	private Long skzhid;
	/**
	 * 状态
0 待审核
1 通过
2 未通过
	 */
	private Integer zt;
	/**
	 * 审核意见
	 */
	private String shyj;
	/**
	 * 
	 */
	private Date sqsj;
	/**
	 * 
	 */
	private Date bgsj;
	private Integer ljsc;
	private Double bzj;

	/**
	 * 简称
	 */
	private String jc;
	/**
	 * 全称
	 */
	private String qc;
	/**
	 * 图片地址
	 */
	private String dptp;
	/**
	 * 联系方式
	 */
	private String lxfs;
	/**
	 * 上班时间
	 */
	private String yysj;
	@TableField(exist = false)
	private String fbmc;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //可接收参数,而不会序列化字符串；READ_ONLY 可序列化为字符串,而不会接收
	@TableField(exist = false)
	private MultipartFile img;
}
