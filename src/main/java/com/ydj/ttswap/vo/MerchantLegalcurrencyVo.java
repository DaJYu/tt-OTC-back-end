package com.ydj.ttswap.vo;

import lombok.Data;

import java.util.Date;

@Data
public class MerchantLegalcurrencyVo {
    private Long id;
    /**
     * 币商钱包
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
     * 保证金可使用额度
     */
    private Double ksyed;
    /**
     * 手续费费率
     */
    private Double fl;
    /**
     * 是否收取手续费\\n0不收\\n1收
     */
    private Integer sfsxf;
    /**
     * 状态\\n0冻结\\n1流通
     */
    private Integer zt;
    /**
     *
     */
    private Date cjsj;
    private Date gxsj;
    private String  fbmc;
    private String  fbfh;
    private String  fbdm;
}
