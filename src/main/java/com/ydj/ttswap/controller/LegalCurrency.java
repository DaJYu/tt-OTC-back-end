package com.ydj.ttswap.controller;

import com.ydj.ttswap.config.SaveFileConfig;
import com.ydj.ttswap.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("base")
public class LegalCurrency {
    @Autowired
    SaveFileConfig saveFileConfig;
    @Autowired
    private LegalCurrencyService legalCurrency;
//    @Autowired
//    private PaymentMethodService paymentMethod;
//    @Autowired
//    private PaymentAccountService paymentAccount;

    /*法币列表*/
//    @RequestMapping("/legalCurrency/list")
//    public R LegalCurrencyList(@RequestParam Map<String, Object> params){
//        PageUtils page = legalCurrency.getLegalCurrencyList(params);
//        return R.ok().put("data",page);
//    }

    /**
     * 法币新增
     */
//    @RequestMapping("/legalCurrency/add")
//    public R LCAdd(LegalCurrencyE legalCurrencyE){
//        Boolean aBoolean= legalCurrency.save(legalCurrencyE);
//        if (aBoolean){
//            return R.ok();
//        } else {
//            return R.error();
//        }
//    }

    /*法币变更*/
//    @RequestMapping("/legalCurrency/stateChanges")
//    public R LCstateChanges(@RequestParam Map<String, Object> params){
//        legalCurrency.LCstateChanges(params);
//        return R.ok();
//    }
//    /*支付方式列表*/
//    @RequestMapping("/paymentMethod/list")
//    public R paymentMethodList(@RequestParam Map<String, Object> params){
//        PageUtils page = paymentMethod.getPaymentMethodList(params);
//        return R.ok().put("data",page);
//    }
//    /*收款账户列表*/
//    @RequestMapping("/paymentAccount/list")
//    public R paymentAccountList(@RequestParam Map<String, Object> params){
//        PageUtils page = paymentAccount.getPaymentAccountList(params);
//        return R.ok().put("data",page);
//    }
//
//    /**
//     * 支付方式新增
//     */
//    @RequestMapping("/paymentMethod/add")
//    public R addPayment(@RequestParam("legend") MultipartFile file,  @RequestParam("name") String name, @RequestParam("type") int type){
//        Boolean aBoolean= paymentMethod.add(file,name,type);
//        if (aBoolean){
//            return R.ok();
//        } else {
//            return R.error();
//        }
//    }
//
//    /*支付方式变更*/
//    @RequestMapping("/paymentMethod/stateChanges")
//    public R PCstateChanges(@RequestParam Map<String, Object> params){
//        paymentMethod.getPCStateChanges(params);
//        return R.ok();
//    }

}
