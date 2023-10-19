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
 * 门户申请结果
 * 
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
@Data
@TableName("gator_apply_result")
public class GatorApplyResultEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	private String jc;
	private String qc;
	private String gj;
	private String gwdz;
	private String ip;
	private String qbdz;
	private String tb;
	private Integer zt;
	/**
	 * 审核意见
	 */
	private String yj;
	/**
	 * 审核人
	 */
	private String shy;
	/**
	 * 
	 */
	private Date cjsj;
	private Integer ljsc;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //可接收参数,而不会序列化字符串；READ_ONLY 可序列化为字符串,而不会接收
	@TableField(exist = false)
	private MultipartFile img;

}
