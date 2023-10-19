package com.ydj.ttswap.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CoinDealerExamineVo {
    private Long sqid;
    private String bsqb;
    private Integer sqlb;
    private Long fbid;
    private String zfr;
    private Long skzhid;
    private Integer zt;
    private String shyj;
    private Date sqsj;
    private Date bgsj;
    private Integer ljsc;
    private Double bzj;
    private Long bsid;
    /**
     * 币商钱包地址
     */
//    private String bsqb;
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
    private Integer bzjid;
    private String zhmc;
    private String khmc;
    private String zhm;
    private Integer zflx;
    private String skm;
    private String fbmc;
    private String zffs;

}
