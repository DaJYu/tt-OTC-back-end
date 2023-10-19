package com.ydj.ttswap.vo;

import lombok.Data;

import java.util.Date;

@Data
public class OtcListVo {

    private Long spid;
    /**
     * 法币id
     */
    private Long fbid;
    /**
     * 法币名称
     */
    private String fbmc;
    private String fbfh;
    private String fbdm;
    /**
     * 币种合约地址
     */
    private String bzhy;
    /**
     * 币种名称
     */
    private String bzmc;
    /**
     * 币种外部链接
     */
    private String xnblj;
    /**
     * 币种单价
     */
    private Double bzdj;
    /**
     * 币种数量
     */
    private Double bzsl;
    /**
     * 单笔最高成交额限制
     */
    private Double zgxe;
    /**
     * 单笔最低成交额限制
     */
    private Double zdxe;
    /**
     * 截止时间
     */
    private Date jzsj;
    /**
     * 当前成交数量
     */
    private Double cjsl;
    /**
     * 当前被锁定数量
     */
    private Double sdsl;
    /**
     * 当前库存数量
     */
    private Double kcsl;
    /**
     * 商品出售类型
     0 买入
     1 卖出
     */
    private Integer cslx;
    /**
     * 状态
     1 上架
     0 下架
     */
    private Integer zt;
    /**
     *
     */
    private Date cjsj;
    /**
     *
     */
    private Date gxsj;
    private String cjr;
    private Double ksyed;
    private String jc;
    private String qc;
    private String dptp;
    private String zffs;
    private Integer xdl;
    private Integer wcl;
}
