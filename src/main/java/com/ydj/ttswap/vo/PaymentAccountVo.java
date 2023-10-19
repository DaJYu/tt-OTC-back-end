package com.ydj.ttswap.vo;

import com.ydj.ttswap.entity.PaymentAccountEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PaymentAccountVo {
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
    String fbmc;
    String zffs;

}
