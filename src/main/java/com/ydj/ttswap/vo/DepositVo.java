package com.ydj.ttswap.vo;

import lombok.Data;

import java.util.Date;

@Data
public class DepositVo {
    private Long bzjid;
    /**
     * 币商id
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
     * 保证金锁定金额
     */
    private Double sded;
    /**
     * 保证金被扣额度
     */
    private Double kced;
    /**
     * 保证金可使用额度
     */
    private Double ksyed;
    private Double kcsxf;
    private Double tqsxf;
    private Double kcwgfy;
    private Double tqwgfy;
    /**
     * 状态\\n0冻结\\n1流通
     */
    private Integer zt;
    /**
     *
     */
    private Date cjsj;
    private Date xgsj;
    private String  fbmc;
    private String  bsqc;
    private String  bsjc;
}
