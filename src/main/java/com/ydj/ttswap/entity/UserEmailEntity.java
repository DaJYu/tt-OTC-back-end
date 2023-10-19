package com.ydj.ttswap.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户邮箱
 * 
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-08-14 18:40:28
 */
@Data
@TableName("user_email")
public class UserEmailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private String mail;
	/**
	 * 
	 */
	private Date cjsj;

}
