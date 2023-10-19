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
 * 币商信息表
 * 
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
@Data
@TableName("otc_coin_dealer")
public class CoinDealerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long bsid;
	/**
	 * 币商钱包地址
	 */
	private String bsqb;
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
	/**
	 * 状态\n0冻结\n1流通 2 待审核
	 */
	private Integer zt;
	/**
	 * 创建时间
	 */
	private Date cjsj;
	/**
	 * 
	 */
	private Date gxsj;
	private Integer ljsc;

	/**
	 *
	 * 上传文件值
	 * */
//	@NotEmpty(message = "品牌logo图片不能为空",groups={AddGroup.class})
//	@JSONField(serialize = false)//隐藏实体类
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //可接收参数,而不会序列化字符串；READ_ONLY 可序列化为字符串,而不会接收
	@TableField(exist = false)
	private MultipartFile img;
}
