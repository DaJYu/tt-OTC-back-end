package com.ydj.ttswap.controller;

import java.util.*;

import com.ydj.ttswap.config.SaveFileConfig;
import com.ydj.ttswap.entity.PaymentMethodEntity;
import com.ydj.ttswap.vo.PaymentAccountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ydj.ttswap.entity.PaymentAccountEntity;
import com.ydj.ttswap.service.PaymentAccountService;
import com.ydj.ttswap.utils.R;


/**
 * 收款账户配置信息表
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
@RestController
@RequestMapping("base/paymentaccount")
public class PaymentAccountController {
    @Autowired
    private PaymentAccountService paymentAccountService;

    @Autowired
    SaveFileConfig saveFile;
    /**
     * 列表
     */
    @RequestMapping("/{jspd}/list")
    public R list(@PathVariable("jspd") String js,@RequestParam Map<String, Object> params){
        List<PaymentAccountVo> page = paymentAccountService.queryPage(js,params);

        return R.ok().put("data", page);
    }

    /**
     * 筛选列表
     */
    @RequestMapping("/{jspd}/listSel")
    public R listSel(@PathVariable("jspd") String js,@RequestParam Map<String, Object> params){
        List<PaymentAccountEntity> page = paymentAccountService.listSel(js,params);

        return R.ok().put("data", page);
    }
    /**
     * 筛选列表
     */
    @RequestMapping("/{jspd}/one/listSel")
    public R listSels(@PathVariable("jspd") String js,@RequestParam Map<String, Object> params){
        List<PaymentAccountEntity> page = paymentAccountService.listSel(js,params);
        if (params.get("lb").equals("0")){
            String a = params.get("pd").toString();
            if (page.size()>0){
                int num = Integer.parseInt(a) % page.size();
                page = Collections.singletonList(page.get(num));
            }
        }

        return R.ok().put("data", page);
    }
    /**
     * 支付方式筛选列表
     */
    @RequestMapping("/{jspd}/paySel")
    public R paySel(@PathVariable("jspd") String js,@RequestParam Map<String, Object> params){
        List<PaymentMethodEntity> page = paymentAccountService.paySel(js,params);

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/{jspd}/info/{id}")
    public R info(@PathVariable("id") Integer id){
		PaymentAccountEntity paymentAccount = paymentAccountService.getById(id);

        return R.ok().put("paymentAccount", paymentAccount);
    }

    /**
     * 保存
     * 门户新建支付账户
     * base/paymentaccount/admin/save
     * 币商新建支付账户
     * base/paymentaccount/merchant/save
     * 用户新建支付账户
     * base/paymentaccount/user/save
     */
    @RequestMapping("/{jspd}/save")
    public R save(@PathVariable("jspd") String js,PaymentAccountEntity paymentAccount){

        Boolean b =paymentAccountService.saveAccount(js,paymentAccount);
        if (b){
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 修改
     */
    @RequestMapping("/{jspd}/update")
    public R update(@RequestBody PaymentAccountEntity paymentAccount){
        paymentAccount.setBgsj(new Date());
		paymentAccountService.updateById(paymentAccount);

        return R.ok();
    }

    /**
     * 状态变更
     */
    @RequestMapping("/{jspd}/status")
    public R status(@RequestBody PaymentAccountEntity legal){
        if (legal.getZt()==0||legal.getZt()==1){
            legal.setBgsj(new Date());
            paymentAccountService.updateById(legal);
        }

        return R.ok();
    }
    /**
     * 状态变更冻结
     */
    @RequestMapping("/{jspd}/frozen")
    public R frozen(@RequestBody PaymentAccountEntity legal){
        legal.setZt(0);
        legal.setBgsj(new Date());
        paymentAccountService.updateById(legal);

        return R.ok();
    }
    /**
     * 状态变更解冻
     */
    @RequestMapping("/{jspd}/thaw")
    public R thaw(@RequestBody PaymentAccountEntity legal){
        legal.setZt(1);
        legal.setBgsj(new Date());
        paymentAccountService.updateById(legal);

        return R.ok();
    }
    /**
     * 删除
     */
    @RequestMapping("/{jspd}/delete")
    public R delete(@RequestBody PaymentAccountEntity legal){
		paymentAccountService.removeById(legal);

        return R.ok();
    }

}
