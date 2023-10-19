package com.ydj.ttswap.vo;

import com.ydj.ttswap.entity.PaymentMethodEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class LegalCurrencyVo {
    private Long id;
    private Long fbid;
    private String name;
    private String fh;
    private String dm;
    private Double bzjed;
    private Integer zt;
    private Date cjsj;
    private Date bgsj;
    private List<Long> zffsid;
    private String zffs;
    private String zffsID;
    private Double flz;
    private Integer sfsxf;

}
