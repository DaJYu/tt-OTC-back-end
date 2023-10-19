package com.ydj.ttswap.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 交易凭据
 * 
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
@Data
public class VoucherVo implements Serializable {

	/**
	 * 视频凭据
	 */
	private String sppz;
	/**
	 *
	 */
	private Date cjsj;
}