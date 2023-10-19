package com.ydj.ttswap.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

/**
 * 参数vo
 * 
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
@Data
public class ParameterVo {

	private Long fbid; //法币id
	private Long zhid; //收款账户id
	private Double bzj;	//保证金额度
	private String zfr;	//支付人
	private String bsid;	//币商钱包地址
	private String yhid;	//币商钱包地址
	private Long bzjid;	//保证金

	private Long ddid;	//订单id
	private Long skzh;	//收款账户id
	private Integer ztdm;	//状态代码
	private Integer zflx;	//支付类型
	private String dkr;//打款人
	private String hxz;//支付代币哈希值


	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //可接收参数,而不会序列化字符串；READ_ONLY 可序列化为字符串,而不会接收
	private MultipartFile img;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //可接收参数,而不会序列化字符串；READ_ONLY 可序列化为字符串,而不会接收
	private MultipartFile[] imgs;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //可接收参数,而不会序列化字符串；READ_ONLY 可序列化为字符串,而不会接收
	private MultipartFile sp;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //可接收参数,而不会序列化字符串；READ_ONLY 可序列化为字符串,而不会接收
	private MultipartFile[] sps;

}