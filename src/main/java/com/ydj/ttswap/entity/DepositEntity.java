package com.ydj.ttswap.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 保证金信息表
 * 
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
@Data
@TableName("otc_deposit")
public class DepositEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long bzjid;
	/**
	 * 币商id
	 */
	private String bsid;
	/**
	 * 法币id
	 */
	private Long fbid;
	/**
	 * 保证金缴纳金额
	 */
	private Double bzj;
	/**
	 * 保证金锁定金额
	 */
	private Double sded;
	/**
	 * 保证金被扣额度
	 */
	private Double kced;
	/**
	 * 保证金可使用额度
	 */
	private Double ksyed;
	/**
	 * 扣除手续费
	 */
	private Double kcsxf;
	/**
	 * 提取手续费金额
	 */
	private Double tqsxf;
	/**
	 * 扣除违规费用
	 */
	private Double kcwgfy;
	/**
	 * 提取违规费
	 */
	private Double tqwgfy;
	/**
	 * 状态\\n0冻结\\n1流通
	 */
	private Integer zt;
	/**
	 * 
	 */
	private Date cjsj;
	/**
	 * 
	 */
	private Date xgsj;
	private Integer ljsc;

}
