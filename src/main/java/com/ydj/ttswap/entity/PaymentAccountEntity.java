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
 * 收款账户配置信息表
 * 
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
@Data
@TableName("otc_payment_account")
public class PaymentAccountEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long zhid;
	/**
	 * 法币id
	 */
	private Long fbid;
	/**
	 * 支付方式id
	 */
	private Long zffsid;
	/**
	 * 账户名称
	 */
	private String zhmc;
	/**
	 * 开户姓名
	 */
	private String khmc;
	/**
	 * 支付账户或收款码地址
	 */
	private String zhm;
	/**
	 * 创建人
	 */
	private String cjr;
	/**
	 * 角色\n0 门户\n1 币商\n2 用户
	 */
	private Integer js;
	private Integer zt;
	//支付类别 0 账号; 1 收款码; 2 现金交易
	private Integer zflx;
	/**
	 * 交易地址
	 */
	private String dz;
	/**
	 * 联系方式
	 */
	private String lxfs;
	/**
	 * 联系人
	 */
	private String lxr;
	/**
	 * 营业时间段
	 */
	private String yysj;
	/**
	 * 交易位置图片
	 */
	private String wztp;
	private String skm;
	/**
	 * 
	 */
	private Date cjsj;
	private Date bgsj;
	private Integer ljsc;

	/**
	 *
	 * 上传文件值
	 * */
//	@NotEmpty(message = "品牌logo图片不能为空",groups={AddGroup.class})
//	@JSONField(serialize = false)//隐藏实体类
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //可接收参数,而不会序列化字符串；READ_ONLY 可序列化为字符串,而不会接收
	@TableField(exist = false)
	private MultipartFile ewm;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //可接收参数,而不会序列化字符串；READ_ONLY 可序列化为字符串,而不会接收
	@TableField(exist = false)
	private MultipartFile pmdztp;
}
