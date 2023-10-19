package com.ydj.ttswap.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 门户OTC收益提取记录表
 * 
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-07-19 15:01:45
 */
@Data
@TableName("gator_otc_income_draw_record")
public class GatorOtcIncomeDrawRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 保证金id
	 */
	@TableId
	private Long bzjid;
	private Long fbid;
	/**
	 * 手续费金额
	 */
	private Double sxfje;
	/**
	 * 违规费金额
	 */
	private Double wgfje;
	/**
	 * 操作账号
	 */
	private String czr;
	/**
	 * 创建时间
	 */
	private Date cjsj;

}
