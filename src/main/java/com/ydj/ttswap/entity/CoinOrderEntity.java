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
 * 虚拟币、物品图标对应表
 * 
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
@Data
@TableName("otc_coin_order")
public class CoinOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 图标地址
	 */
	private String tb;
	/**
	 * 合约地址
	 */
	private String hydz;
	/**
	 * 名称
	 */
	private String mc;
	private String qc;
	private String cjr;
	/**
	 * 类型
0 虚拟币
1 物品
	 */
	private Integer lz;
	private Integer zt;
	private Integer lx;
	/*来源\n0 市场\n1 门户*/
	private Integer ly;
	/*手续费级别*/
	private Double sxfjb;
	/**
	 * 
	 */
	private Date cjsj;
	private Date xgsj;
	private Integer ljsc;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //可接收参数,而不会序列化字符串；READ_ONLY 可序列化为字符串,而不会接收
	@TableField(exist = false)
	private MultipartFile img;

}
