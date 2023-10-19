package com.ydj.ttswap.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 保证金历史记录信息表
 * 
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
@Data
@TableName("otc_deposit_history")
public class DepositHistoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 保证金id
	 */
	private Long bzjid;
	/**
	 * 币商地址
	 */
	@TableId
	private String bsid;
	/**
	 * 法币
	 */
	private Long fbid;
	/**
	 * 当前保证金
	 */
	private Double bzj;
	/**
	 * 已锁定金额
	 */
	private Double sded;
	/**
	 * 锁定原因
	 */
	private String sdyy;
	/**
	 * 扣除额度
	 */
	private Double kced;
	/**
	 * 扣除原因
	 */
	private String kcyy;
	/**
	 * 解锁额度
	 */
	private Double jsed;
	/**
	 * 可使用额度
	 */
	private Double ksyed;
	/**
	 * 缴纳额度
	 */
	private Double jned;
	/*退还保证金*/
	private Double tcbzj;
	private Double kcsxf;
	private Integer zt;
	/**
	 * 
	 */
	private Date cjsj;
	private Integer ljsc;

	@TableField(exist = false)
	private String fbmc;
}
