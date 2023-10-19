package com.ydj.ttswap.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.ydj.ttswap.config.SaveFileConfig;
import com.ydj.ttswap.entity.PaymentMethodEntity;
import com.ydj.ttswap.mapper.PaymentMethodMapper;
import com.ydj.ttswap.utils.ServiceSaveFile;
import com.ydj.ttswap.vo.PaymentAccountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ydj.ttswap.mapper.PaymentAccountMapper;
import com.ydj.ttswap.entity.PaymentAccountEntity;
import com.ydj.ttswap.service.PaymentAccountService;


@Service("paymentAccountService")
public class PaymentAccountServiceImpl extends ServiceImpl<PaymentAccountMapper, PaymentAccountEntity> implements PaymentAccountService {

    @Autowired
    private PaymentAccountService paymentAccountService;
    @Autowired
    SaveFileConfig saveFile;
    @Autowired
    PaymentMethodMapper methodMapper;
    @Autowired
    PaymentAccountMapper mapper;
    @Override
    public List<PaymentAccountVo> queryPage(String js,Map<String, Object> params) {
        QueryWrapper<PaymentAccountVo> wrapper = new QueryWrapper<>();
        if (js.equals("merchant")){
            wrapper.eq("a.cjr",params.get("bsid"));
            wrapper.eq("a.js",1);
        }
        if (js.equals("user")){
            wrapper.eq("a.cjr",params.get("qbdz"));
            wrapper.eq("a.js",2);
        }
        if (js.equals("admin")){
            wrapper.eq("a.js",0);
        }
        String id= (String) params.get("fbid");
//        wrapper.eq("ljsc",0);
        if (!StringUtils.isEmpty(id)){
            wrapper.eq("a.fbid",id);
        }
        wrapper.eq("a.ljsc",0)
                .eq("b.ljsc",0)
                .eq("c.ljsc",0);
        List<PaymentAccountVo> page = methodMapper.findByJoin(wrapper);
        return page;
    }

    @Override
    public Boolean saveAccount(String js,PaymentAccountEntity paymentAccount) {
//        PaymentAccountEntity paymentAccount=new PaymentAccountEntity();
        if (js.equals("admin")){
            paymentAccount.setJs(0);
        }if (js.equals("merchant")){
            paymentAccount.setJs(1);
        }if (js.equals("user")){
            paymentAccount.setJs(2);
            paymentAccount.setZt(1);
        }
        boolean fileUpload = true;
        if (paymentAccount.getZflx().equals(1)){
            String fname=paymentAccount.getEwm().getOriginalFilename();
            String fileName=UUID.randomUUID()+fname.substring(fname.lastIndexOf("."), fname.length());;
            String fileUrl=saveFile.getProfile();
            fileUpload = ServiceSaveFile.singleFileUpload(paymentAccount.getEwm(), fileName, fileUrl + "/icon-save/");
            paymentAccount.setSkm(fileName);
        }
        if (paymentAccount.getZflx().equals(2) && paymentAccount.getPmdztp() !=null){
            String fname=paymentAccount.getPmdztp().getOriginalFilename();
            String fileName=UUID.randomUUID()+fname.substring(fname.lastIndexOf("."), fname.length());;
            String fileUrl=saveFile.getProfile();
            fileUpload = ServiceSaveFile.singleFileUpload(paymentAccount.getPmdztp(), fileName, fileUrl + "/icon-save/");
            paymentAccount.setWztp(fileName);
        }
        if (fileUpload){
            paymentAccountService.save(paymentAccount);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<PaymentAccountEntity> listSel(String js, Map<String, Object> params) {
        QueryWrapper<PaymentAccountEntity> wrapper = new QueryWrapper<>();

        wrapper.eq("zt",1)
                .eq("zflx",params.get("lb"))
                .eq("zffsid",params.get("id"));
        if (js.equals("merchant")){
            wrapper.eq("js",0)
                    .eq("fbid",params.get("fbid"));
        }
        if (js.equals("user")){
            wrapper.eq("js",1)
                    .eq("cjr",params.get("bsqb"));
        }
        if (js.equals("my")){
            wrapper.eq("js",2)
                    .eq("cjr",params.get("bsqb"));
        }
        return mapper.selectList(wrapper);

    }

    @Override
    public List<PaymentMethodEntity> paySel(String js, Map<String, Object> params) {
//        QueryWrapper<PaymentAccountEntity> wrapper = new QueryWrapper<>();
//            wrapper.eq("zt",1)
//                    .eq("cjr",params.get("bsqb"));
        List<PaymentMethodEntity> list=null;
        if (js.equals("user")){
            list =mapper.selectListUser((String) params.get("spid"));
        } else {
            list =mapper.selectLists((String) params.get("bsqb"));
        }
//        List<PaymentMethodEntity> list =mapper.selectLists((String) params.get("bsqb"));
        return list;
    }

//    @Override
//    public IPage<PaymentAccountVo> queryPage(Page<PaymentAccountVo> page) {
//        QueryWrapper<PaymentAccountVo> wrapper = new QueryWrapper<>();
//        return methodMapper.findByJoin(page,wrapper);
//    }

}