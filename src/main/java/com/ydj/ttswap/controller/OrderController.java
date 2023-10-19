package com.ydj.ttswap.controller;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ydj.ttswap.config.SaveFileConfig;
import com.ydj.ttswap.constant.OrderConstant;
import com.ydj.ttswap.entity.*;
import com.ydj.ttswap.service.*;
import com.ydj.ttswap.utils.MathUtils;
import com.ydj.ttswap.utils.ServiceSaveFile;
import com.ydj.ttswap.vo.ParameterVo;
import com.ydj.ttswap.vo.VoucherVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.utils.R;



/**
 * 订单信息表
 *
 * @author Ray Yu
 * @email beifengnanxing@foxmail.com
 * @date 2023-03-29 10:40:47
 */
@RestController
@RequestMapping("otc/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderChangeService changeService;
    @Autowired
    private VoucherService voucherService;
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private DepositService depositService;
    @Autowired
    private DepositHistoryService depositHistoryService;
    @Autowired
    private PaymentAccountService paymentAccountService;
    @Autowired
    private LegalCurrencyService legalCurrencyService;

    @Autowired
    SaveFileConfig saveFile;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderService.queryPage(params);

        return R.ok().put("data", page);
    }


    /**
     * 列表
     * 币商merchant
     * 用户user
     */
    @RequestMapping("/{jspd}/list")
    public R lists(@PathVariable("jspd") String js,@RequestParam Map<String, Object> params){
        PageUtils page = orderService.queryPages(js,params);

        return R.ok().put("data", page);
    }

    /**
     * 详情信息
     */
    @RequestMapping("/{jspd}/info")
    public R info(@RequestParam Integer id){
        QueryWrapper<OrderChangeEntity> wrapper = new QueryWrapper<>();
        QueryWrapper<VoucherEntity> voucher = new QueryWrapper<>();
        wrapper.eq("ddid",id)
                .orderByDesc("ztsj");
         List list= changeService.list(wrapper);
		OrderEntity order = orderService.getById(id);
        PaymentAccountEntity dkzh= paymentAccountService.getById(order.getSkzh());
        voucher.eq("ddid",id)
                .orderByAsc("cjsj");
        List<VoucherEntity> list1= voucherService.list(voucher);
        List<String> yhtp = list1.stream().filter(i -> i.getJs().equals(2)&&i.getTppz()!=null).map(i->{
            return i.getTppz();
        }).collect(Collectors.toList());
        List<String> bstp = list1.stream().filter(i -> i.getJs().equals(1)&&i.getTppz()!=null).map(i->{
            return i.getTppz();
        }).collect(Collectors.toList());
        List<VoucherVo> yhsp = list1.stream().filter(i -> i.getJs().equals(2)&&i.getSppz()!=null).map(i->{
            VoucherVo vo =new VoucherVo();
            BeanUtils.copyProperties(i,vo);
            return vo;
        }).collect(Collectors.toList());
        List<VoucherVo> bssp = list1.stream().filter(i -> i.getJs().equals(1)&&i.getSppz()!=null).map(i->{
            VoucherVo vo =new VoucherVo();
            BeanUtils.copyProperties(i,vo);
            return vo;
        }).collect(Collectors.toList());
        return R.ok()
                .put("status", list)
                .put("yhtppz",yhtp)
                .put("bstppz",bstp)
                .put("yhsppz",yhsp)
                .put("bssppz",bssp)
                .put("zhxx",dkzh)
                .put("ddxx",order);
    }

    /**
     * 下单
     */
    @RequestMapping("/user/save")
    @Transactional(rollbackFor = Exception.class)
    public R save(@RequestBody OrderEntity order){
    //x下单
        LegalCurrencyEntity fb = legalCurrencyService.getById(order.getFbid());
        if (fb.getSfsxf()==1){
            Double sxf = MathUtils.mul(order.getJe(),fb.getFl());//order.getJe() * fb.getFl();
            order.setSxf(sxf);
            order.setFl(fb.getFl());
        }
        order.setZt(OrderConstant.StatusEnum.PLACE_ORDER.getCode());
        order.setZdsx(new Date(new Date().getTime()+ OrderConstant.USER_ORDER_TIME_LIMIT));
		orderService.save(order);

        OrderChangeEntity changeEntity=new OrderChangeEntity();
        changeEntity.setDdid(order.getDdid());
        changeEntity.setSpid(order.getSpid());
        changeEntity.setDdzt(OrderConstant.StatusEnum.PLACE_ORDER.getCode());
        changeEntity.setJs(OrderConstant.JsEnum.USER.getCode());
        changeService.save(changeEntity);
    //商品数量变更
        CommodityEntity spsl = commodityService.getById(order.getSpid());
        DepositEntity bzjid =  depositService.getOne(new QueryWrapper<DepositEntity>().eq("bsid",order.getBsdz()).eq("fbid",order.getFbid()));
        Double sdsl=spsl.getSdsl();
        Double kcsl=spsl.getKcsl();
        Double sded=bzjid.getSded();
        Double ksyed=bzjid.getKsyed();
        if (spsl.getSdsl()==null){
            sdsl=0.0;
        }
        if (kcsl<order.getSl()||ksyed<order.getJe()){
            return R.error("库存不足，请重新下单！");
        }
        if (sded==null){
            sded=0.0;
        }
        System.out.println("*************"+sdsl+"#########"+order.getSl()+"&&&&&&&"+(sdsl+order.getSl()));
        CommodityEntity commodityEntity =new CommodityEntity();
        commodityEntity.setSpid(spsl.getSpid());
        commodityEntity.setSdsl(MathUtils.add(sdsl,order.getSl()));// sdsl+order.getSl());
        commodityEntity.setKcsl(MathUtils.sub(kcsl,order.getSl()));// kcsl-order.getSl());
        commodityEntity.setGxsj(new Date());
        commodityService.updateById(commodityEntity);
    //保证金变更
        DepositEntity depositEntity = new DepositEntity();
        depositEntity.setBzjid(bzjid.getBzjid());
        depositEntity.setSded(MathUtils.add(sded,order.getJe()));// sded+order.getJe());
        depositEntity.setKsyed(MathUtils.sub(ksyed,order.getJe()));// ksyed-order.getJe());
        depositEntity.setXgsj(new Date());
        depositService.updateById(depositEntity);

        DepositHistoryEntity historyEntity =new DepositHistoryEntity();
        historyEntity.setBzjid(bzjid.getBzjid());
        historyEntity.setBsid(bzjid.getBsid());
        historyEntity.setFbid(bzjid.getFbid());
        historyEntity.setSded(order.getJe());
        depositHistoryService.save(historyEntity);

        return R.ok().put("data",order.getDdid());
    }


    /**
     * 用户完成付款
     */
    @RequestMapping("/user/payment")
    @Transactional(rollbackFor = Exception.class)
    public R paymentU(ParameterVo vo){
        OrderEntity orders=orderService.getById(vo.getDdid());
        OrderEntity order=new OrderEntity();
        order.setDdid(vo.getDdid());
        order.setSkzh(vo.getSkzh());
        order.setDkr(vo.getDkr());
        order.setZt(OrderConstant.StatusEnum.M_C_P.getCode());
        order.setZdsx(new Date(new Date().getTime()+ OrderConstant.ORDER_STATUS_TIME_LIMIT));
        order.setGxsj(new Date());
//        orderService.updateById(order);

        OrderChangeEntity changeEntity=new OrderChangeEntity();
        changeEntity.setSpid(orders.getSpid());
        changeEntity.setDdid(orders.getDdid());
        changeEntity.setDdzt(order.getZt());
        changeEntity.setJs(OrderConstant.JsEnum.USER.getCode());
//        changeService.save(changeEntity);

        VoucherEntity voucherEntity=new VoucherEntity();
        voucherEntity.setDdid(orders.getDdid());
        voucherEntity.setDdzt(orders.getZt());
        voucherEntity.setJs(OrderConstant.JsEnum.USER.getCode());
        if (vo.getZtdm().equals(OrderConstant.StatusEnum.PLACE_ORDER.getCode())&&vo.getZtdm().equals(orders.getZt())&&vo.getYhid().equals(orders.getYhdz())){
            if (vo.getImgs() != null) {
                boolean fileUpload = false;
                List<String> fileNames = new ArrayList<>();
                for (int i=0;i<vo.getImgs().length;i++) {
                    String fname=vo.getImgs()[i].getOriginalFilename();
                    String fileName=vo.getDdid()+"-"+System.currentTimeMillis()+fname.substring(fname.lastIndexOf("."), fname.length());;
                    String fileUrl=saveFile.getProfile();
                    fileUpload = ServiceSaveFile.singleFileUpload(vo.getImgs()[i], fileName, fileUrl + "/voucher-save/");
                    fileNames.add(fileName);
                    if (!fileUpload){
                        continue;
                    }
                }
                if (fileUpload) {
                    orderService.updateById(order);
                    changeService.save(changeEntity);
                    fileNames.forEach(fileName->{
                        voucherEntity.setTppz(fileName);
                        voucherService.save(voucherEntity);
                    });
                    return R.ok();
                } else {
                    return R.error("图片保存失败，请重新尝试！");
                }
            } else {
                if(vo.getZflx()!=2){
                    return R.error("图片不能为空！");
                } else {
                    orderService.updateById(order);
                    changeService.save(changeEntity);
                    return R.ok();
                }
            }
        } else {
            return R.error("异常操作！");
        }

    }


    /**
     * 币商确认收款
     */
    @RequestMapping("/merchant/collection")
    @Transactional(rollbackFor = Exception.class)
    public R collectionM(@RequestBody ParameterVo vo){

        OrderEntity orders=orderService.getById(vo.getDdid());
        if (vo.getZtdm().equals(orders.getZt())&&vo.getBsid().equals(orders.getBsdz())&&vo.getZtdm().equals(OrderConstant.StatusEnum.M_C_P.getCode())||vo.getZtdm().equals(OrderConstant.StatusEnum.M_NO_P_MERCHANT_UP_IMG.getCode())||vo.getZtdm().equals(OrderConstant.StatusEnum.M_NO_P_MERCHANT_UP_VIDEO.getCode())){
            OrderEntity order=new OrderEntity();
            order.setDdid(vo.getDdid());
            order.setZt(OrderConstant.StatusEnum.U_C_C.getCode());
            order.setHxz(vo.getHxz());
            order.setZdsx(new Date(new Date().getTime()+ OrderConstant.ORDER_STATUS_TIME_LIMIT));
            order.setGxsj(new Date());
            orderService.updateById(order);

            OrderChangeEntity changeEntity=new OrderChangeEntity();
            changeEntity.setSpid(orders.getSpid());
            changeEntity.setDdid(orders.getDdid());
            changeEntity.setDdzt(order.getZt());
            changeEntity.setJs(OrderConstant.JsEnum.C_M.getCode());
            changeService.save(changeEntity);

            return R.ok();
        } else {
            return R.error("操作异常");
        }
    }


    /**
     * 用户确认收币
     */
    @RequestMapping("/user/collectCurrency")
    @Transactional(rollbackFor = Exception.class)
    public R collectCurrencyU(@RequestBody ParameterVo vo){

        OrderEntity order=orderService.getById(vo.getDdid());
        if (vo.getZtdm().equals(order.getZt())&&vo.getYhid().equals(order.getYhdz())&&vo.getZtdm().equals(OrderConstant.StatusEnum.U_C_C.getCode())||vo.getZtdm().equals(OrderConstant.StatusEnum.U_NO_C_USER_UP_VIDEO.getCode())){

            OrderEntity orders=new OrderEntity();
            orders.setDdid(vo.getDdid());
            orders.setZt(OrderConstant.StatusEnum.TRANSACTION_COMPLETED.getCode());
            orders.setZdsx(null);
            orders.setGxsj(new Date());
            orderService.updateById(orders);

            OrderChangeEntity changeEntity=new OrderChangeEntity();
//        order=orderService.getById(vo.getDdid());
            changeEntity.setSpid(order.getSpid());
            changeEntity.setDdid(order.getDdid());
            changeEntity.setDdzt(orders.getZt());
            changeEntity.setJs(OrderConstant.JsEnum.USER.getCode());
            changeService.save(changeEntity);

//        order=orderService.getById(vo.getDdid());
            CommodityEntity spsl = commodityService.getById(order.getSpid());
            DepositEntity bzjid =  depositService.getOne(new QueryWrapper<DepositEntity>().eq("bsid",order.getBsdz()).eq("fbid",order.getFbid()));

            Double sdsl=spsl.getSdsl();
            Double cjsl=spsl.getCjsl();
            Double kcsl=spsl.getKcsl();
            Double sded=bzjid.getSded();
            Double ksyed=bzjid.getKsyed();
            if (sdsl==null){
                sdsl=0.0;
            }
            if (cjsl==null){
                cjsl=0.0;
            }
            if (kcsl==null){
                kcsl=0.0;
            }
            if (sded==null){
                sded=0.0;
            }
            if (ksyed==null){
                ksyed=0.0;
            }

            //商品数量变更
            CommodityEntity commodityEntity =new CommodityEntity();
            commodityEntity.setSpid(spsl.getSpid());
            commodityEntity.setSdsl(MathUtils.sub(sdsl,order.getSl()));// sdsl - order.getSl());
            commodityEntity.setCjsl(MathUtils.add(cjsl,order.getSl()));// cjsl + order.getSl());
            commodityEntity.setGxsj(new Date());
            commodityService.updateById(commodityEntity);
            //保证金变更
            DepositEntity depositEntity = new DepositEntity();
            depositEntity.setBzjid(bzjid.getBzjid());
            depositEntity.setSded(MathUtils.sub(sded,order.getJe()));// sded - order.getJe());
            depositEntity.setKsyed(MathUtils.add(ksyed,MathUtils.sub(order.getJe(),order.getSxf())));// ksyed + order.getJe() - order.getSxf());
            depositEntity.setKcsxf(MathUtils.add(bzjid.getKcsxf(),order.getSxf()));// bzjid.getKcsxf() + order.getSxf());
            depositEntity.setXgsj(new Date());
            depositService.updateById(depositEntity);

            DepositHistoryEntity historyEntity =new DepositHistoryEntity();
            historyEntity.setBzjid(bzjid.getBzjid());
            historyEntity.setBsid(bzjid.getBsid());
            historyEntity.setFbid(bzjid.getFbid());
            historyEntity.setJsed(order.getJe());
            historyEntity.setKcsxf(order.getSxf());
            depositHistoryService.save(historyEntity);
            return R.ok();
        } else {
            return R.error("操作异常");
        }
    }


    /**
     * 用户完成付币（pay）
     */
    @RequestMapping("/user/pay")
    @Transactional(rollbackFor = Exception.class)
    public R pay(@RequestBody ParameterVo vo){

        OrderEntity orders=orderService.getById(vo.getDdid());
        if (vo.getZtdm().equals(OrderConstant.StatusEnum.PLACE_ORDER.getCode())&&vo.getZtdm().equals(orders.getZt())&&vo.getYhid().equals(orders.getYhdz())){

            OrderEntity order=new OrderEntity();
            order.setDdid(vo.getDdid());
            order.setSkzh(vo.getSkzh());
            order.setZt(OrderConstant.StatusEnum.M_C_C.getCode());
            order.setHxz(vo.getHxz());
            order.setZdsx(new Date(new Date().getTime()+ OrderConstant.ORDER_STATUS_TIME_LIMIT));
            order.setGxsj(new Date());
            orderService.updateById(order);

            OrderChangeEntity changeEntity=new OrderChangeEntity();
            changeEntity.setSpid(orders.getSpid());
            changeEntity.setDdid(orders.getDdid());
            changeEntity.setDdzt(order.getZt());
            changeEntity.setJs(OrderConstant.JsEnum.USER.getCode());
            changeService.save(changeEntity);
//
//        VoucherEntity voucherEntity=new VoucherEntity();
//        voucherEntity.setDdid(order.getDdid());
//        voucherEntity.setDdzt(order.getZt());
//        voucherEntity.setJs(OrderConstant.JsEnum.USER.getCode());
//
//        if (vo.getImg() != null) {
//            String fname=vo.getImg().getOriginalFilename();
//            String fileName=vo.getDdid()+":"+System.currentTimeMillis()+fname.substring(fname.lastIndexOf("."), fname.length());;
//            String fileUrl=saveFile.getProfile();
//            boolean fileUpload = ServiceSaveFile.singleFileUpload(vo.getImg(), fileName, fileUrl + "/voucher-save/");
//            if (fileUpload) {
//                voucherEntity.setTppz(fileName);
//                voucherService.save(voucherEntity);
//            }
//        }

            return R.ok();
        } else {
            return R.error("操作异常");
        }
    }


    /**
     * 币商确认收币完成付款（collectCurrency）
     */
    @RequestMapping("/merchant/collectCurrency")
    @Transactional(rollbackFor = Exception.class)
    public R collectCurrencyM(@RequestBody ParameterVo vo){

        OrderEntity orders=orderService.getById(vo.getDdid());
        if (vo.getZtdm().equals(orders.getZt())&&vo.getBsid().equals(orders.getBsdz())&&vo.getZtdm().equals(OrderConstant.StatusEnum.M_C_C.getCode())||vo.getZtdm().equals(OrderConstant.StatusEnum.M_NO_C_MERCHANT_UP_IMG.getCode())||vo.getZtdm().equals(OrderConstant.StatusEnum.M_NO_C_MERCHANT_UP_VIDEO.getCode())){

            OrderEntity order=new OrderEntity();
            order.setDdid(vo.getDdid());
            order.setDkr(vo.getDkr());
            order.setZt(OrderConstant.StatusEnum.U_C_P.getCode());
            order.setZdsx(new Date(new Date().getTime()+ OrderConstant.ORDER_STATUS_TIME_LIMIT));
            order.setGxsj(new Date());
            orderService.updateById(order);

            OrderChangeEntity changeEntity=new OrderChangeEntity();
//        order=orderService.getById(vo.getDdid());
            changeEntity.setSpid(orders.getSpid());
            changeEntity.setDdid(orders.getDdid());
            changeEntity.setDdzt(order.getZt());
            changeEntity.setJs(OrderConstant.JsEnum.USER.getCode());
            changeService.save(changeEntity);

            return R.ok();

        } else {
            return R.error("操作异常");
        }
    }


    /**
     * 用户确认收款
     */
    @RequestMapping("/user/collection")
    @Transactional(rollbackFor = Exception.class)
    public R collectionU(@RequestBody ParameterVo vo){
        OrderEntity order=orderService.getById(vo.getDdid());
        if (vo.getZtdm().equals(order.getZt())&&vo.getYhid().equals(order.getYhdz())&&vo.getZtdm().equals(OrderConstant.StatusEnum.U_C_P.getCode())||vo.getZtdm().equals(OrderConstant.StatusEnum.U_NO_P_USER_UP_VIDEO.getCode())){
            OrderEntity orders=new OrderEntity();
            orders.setDdid(vo.getDdid());
            orders.setZt(OrderConstant.StatusEnum.TRANSACTION_COMPLETED.getCode());
            orders.setZdsx(null);
            orders.setGxsj(new Date());
            orderService.updateById(orders);

            OrderChangeEntity changeEntity=new OrderChangeEntity();
            changeEntity.setSpid(order.getSpid());
            changeEntity.setDdid(order.getDdid());
            changeEntity.setDdzt(orders.getZt());
            changeEntity.setJs(OrderConstant.JsEnum.USER.getCode());
            changeService.save(changeEntity);

//        order=orderService.getById(vo.getDdid());
            CommodityEntity spsl = commodityService.getById(order.getSpid());
            DepositEntity bzjid =  depositService.getOne(new QueryWrapper<DepositEntity>().eq("bsid",order.getBsdz()).eq("fbid",order.getFbid()));
            //商品数量变更
            CommodityEntity commodityEntity =new CommodityEntity();
            commodityEntity.setSpid(spsl.getSpid());
            commodityEntity.setSdsl(MathUtils.sub(spsl.getSdsl(),order.getSl()));// spsl.getSdsl() - order.getSl());
            commodityEntity.setCjsl(MathUtils.add(spsl.getCjsl(),order.getSl()));// spsl.getCjsl() + order.getSl());
            commodityEntity.setGxsj(new Date());
            commodityService.updateById(commodityEntity);
            //保证金变更
            DepositEntity depositEntity = new DepositEntity();
            depositEntity.setBzjid(bzjid.getBzjid());
            depositEntity.setSded(MathUtils.sub(bzjid.getSded(),order.getJe()));// bzjid.getSded() - order.getJe());
            depositEntity.setKsyed(MathUtils.add(bzjid.getKsyed(),MathUtils.sub(order.getJe(),order.getSxf())));// bzjid.getKsyed() + order.getJe() - order.getSxf());
            depositEntity.setKcsxf(MathUtils.add(bzjid.getKcsxf(),order.getSxf()));// bzjid.getKcsxf() + order.getSxf());
            depositEntity.setXgsj(new Date());
            depositService.updateById(depositEntity);

            DepositHistoryEntity historyEntity =new DepositHistoryEntity();
            historyEntity.setBzjid(bzjid.getBzjid());
            historyEntity.setBsid(bzjid.getBsid());
            historyEntity.setFbid(bzjid.getFbid());
            historyEntity.setJsed(order.getJe());
            historyEntity.setKcsxf(order.getSxf());
            depositHistoryService.save(historyEntity);
            return R.ok();
        } else {
            return R.error("操作异常");
        }
    }


    /**
     * 用户取消订单
     */
    @RequestMapping("/user/cancel")
    @Transactional(rollbackFor = Exception.class)
    public R cancel(@RequestBody ParameterVo vo){
        OrderEntity order=orderService.getById(vo.getDdid());
        if (vo.getDdid().equals(order.getDdid())&&vo.getYhid().equals(order.getYhdz())) {

            OrderEntity orders=new OrderEntity();
            orders.setDdid(vo.getDdid());
            orders.setZt(OrderConstant.StatusEnum.CANCELLATION_OF_ORDER.getCode());
            orders.setZdsx(null);
            orders.setGxsj(new Date());
            orderService.updateById(orders);

            OrderChangeEntity changeEntity=new OrderChangeEntity();
            changeEntity.setSpid(order.getSpid());
            changeEntity.setDdid(order.getDdid());
            changeEntity.setDdzt(orders.getZt());
            changeEntity.setJs(OrderConstant.JsEnum.USER.getCode());
            changeService.save(changeEntity);

//        order=orderService.getById(vo.getDdid());
            CommodityEntity spsl = commodityService.getById(order.getSpid());
            //商品数量变更
            CommodityEntity commodityEntity =new CommodityEntity();
            commodityEntity.setSpid(spsl.getSpid());
            commodityEntity.setSdsl(MathUtils.sub(spsl.getSdsl(),order.getSl()));// spsl.getSdsl()-order.getSl());
            commodityEntity.setKcsl(MathUtils.add(spsl.getKcsl(),order.getSl()));// spsl.getKcsl()+order.getSl());
            commodityEntity.setGxsj(new Date());
            commodityService.updateById(commodityEntity);
            //保证金变更
            DepositEntity bzjid =  depositService.getOne(new QueryWrapper<DepositEntity>().eq("bsid",order.getBsdz()).eq("fbid",order.getFbid()));
            DepositEntity depositEntity = new DepositEntity();
            depositEntity.setBzjid(bzjid.getBzjid());
            depositEntity.setSded(MathUtils.sub(bzjid.getSded(),order.getJe()));// bzjid.getSded()-order.getJe());
            depositEntity.setKsyed(MathUtils.add(bzjid.getKsyed(),order.getJe()));// bzjid.getKsyed()+order.getJe());
            depositEntity.setXgsj(new Date());
            depositService.updateById(depositEntity);

            DepositHistoryEntity historyEntity =new DepositHistoryEntity();
            historyEntity.setBzjid(bzjid.getBzjid());
            historyEntity.setBsid(bzjid.getBsid());
            historyEntity.setFbid(bzjid.getFbid());
            historyEntity.setJsed(order.getJe());
            depositHistoryService.save(historyEntity);


            return R.ok();
        } else {
            return R.error("操作异常");
        }
    }


    /**
     * 币商确认未收款
     */
    @RequestMapping("/merchant/uncolulected")
    @Transactional(rollbackFor = Exception.class)
    public R uncolulectedM(@RequestBody ParameterVo vo){

        OrderEntity orders=orderService.getById(vo.getDdid());
        if (vo.getZtdm().equals(OrderConstant.StatusEnum.M_C_P.getCode())&&vo.getBsid().equals(orders.getBsdz())){

            OrderEntity order=new OrderEntity();
            order.setDdid(vo.getDdid());
            order.setZt(OrderConstant.StatusEnum.M_NO_P_USER_UP_IMG.getCode());
            order.setZdsx(new Date(new Date().getTime()+ OrderConstant.ORDER_STATUS_TIME_LIMIT));
            order.setGxsj(new Date());
            orderService.updateById(order);

            OrderChangeEntity changeEntity=new OrderChangeEntity();
            changeEntity.setSpid(orders.getSpid());
            changeEntity.setDdid(orders.getDdid());
            changeEntity.setDdzt(order.getZt());
            changeEntity.setJs(OrderConstant.JsEnum.C_M.getCode());
            changeService.save(changeEntity);

            return R.ok();

        } else {
            return R.error("操作异常");
        }
    }

    /**
     * 币商确认未收币
     */
    @RequestMapping("/merchant/uncolulectedCion")
    @Transactional(rollbackFor = Exception.class)
    public R uncolulectedCion(@RequestBody ParameterVo vo){

        OrderEntity orders=orderService.getById(vo.getDdid());
        if (vo.getZtdm().equals(OrderConstant.StatusEnum.M_C_C.getCode())&&vo.getBsid().equals(orders.getBsdz())){

            OrderEntity order=new OrderEntity();
            order.setDdid(vo.getDdid());
            order.setZt(OrderConstant.StatusEnum.M_NO_C_USER_UP_IMG.getCode());
            order.setZdsx(new Date(new Date().getTime()+ OrderConstant.ORDER_STATUS_TIME_LIMIT));
            order.setGxsj(new Date());
            orderService.updateById(order);

            OrderChangeEntity changeEntity=new OrderChangeEntity();
            changeEntity.setSpid(orders.getSpid());
            changeEntity.setDdid(orders.getDdid());
            changeEntity.setDdzt(order.getZt());
            changeEntity.setJs(OrderConstant.JsEnum.C_M.getCode());
            changeService.save(changeEntity);

            return R.ok();

        } else {
            return R.error("操作异常");
        }
    }


    /**
     * 用户上传图片凭证（upImg）
     */
    @RequestMapping("/user/upImg")
    @Transactional(rollbackFor = Exception.class)
    public R upImgU(ParameterVo vo){
        if (vo.getImgs() != null) {
            OrderEntity orders=orderService.getById(vo.getDdid());
            if (vo.getZtdm().equals(orders.getZt())){
                boolean fileUpload = false;
                List<String> fileNames = new ArrayList<>();
                for (int i=0;i<vo.getImgs().length;i++) {
                    String fname=vo.getImgs()[i].getOriginalFilename();
                    String fileName=vo.getDdid()+"-"+System.currentTimeMillis()+fname.substring(fname.lastIndexOf("."), fname.length());;
                    String fileUrl=saveFile.getProfile();
                    fileUpload = ServiceSaveFile.singleFileUpload(vo.getImgs()[i], fileName, fileUrl + "/voucher-save/");
                    fileNames.add(fileName);
                    if (!fileUpload){
                        continue;
                    }
                }
                if (fileUpload) {
                    OrderEntity order=new OrderEntity();
                    order.setDdid(vo.getDdid());
                    if (vo.getZtdm()==OrderConstant.StatusEnum.M_NO_P_USER_UP_IMG.getCode()){
                        order.setZt(OrderConstant.StatusEnum.M_NO_P_MERCHANT_UP_IMG.getCode());
                    }
                    if (vo.getZtdm()==OrderConstant.StatusEnum.U_C_C.getCode()){
                        order.setZt(OrderConstant.StatusEnum.U_NO_C_MERCHANT_UP_IMG.getCode());
                    }
                    if (vo.getZtdm()==OrderConstant.StatusEnum.M_NO_C_USER_UP_IMG.getCode()){
                        order.setZt(OrderConstant.StatusEnum.M_NO_C_MERCHANT_UP_IMG.getCode());
                    }
                    if (vo.getZtdm()==OrderConstant.StatusEnum.U_C_P.getCode()){
                        order.setZt(OrderConstant.StatusEnum.U_NO_P_MERCHANT_UP_IMG.getCode());
                    }
                    order.setZdsx(new Date(new Date().getTime()+ OrderConstant.ORDER_STATUS_TIME_LIMIT));
                    order.setGxsj(new Date());
                    orderService.updateById(order);

                    OrderChangeEntity changeEntity=new OrderChangeEntity();
                    changeEntity.setSpid(orders.getSpid());
                    changeEntity.setDdid(orders.getDdid());
                    changeEntity.setDdzt(order.getZt());
                    changeEntity.setJs(OrderConstant.JsEnum.USER.getCode());
                    changeService.save(changeEntity);

                    VoucherEntity voucherEntity=new VoucherEntity();
                    voucherEntity.setDdid(orders.getDdid());
                    voucherEntity.setDdzt(orders.getZt());
                    voucherEntity.setJs(OrderConstant.JsEnum.USER.getCode());
                    fileNames.forEach(fileName->{
                        voucherEntity.setTppz(fileName);
                        voucherService.save(voucherEntity);
                    });

                    return R.ok();
                } else {
                    return R.error("图片保存失败，请重新尝试！");
                }
            } else {
            return R.error("异常操作！");
        }
        } else {
            return R.error("图片不能为空！");
        }
    }

    /**
     * 用户上传视频凭证 (upVideo)
     */
    @RequestMapping("/user/upVideo")
    @Transactional(rollbackFor = Exception.class)
    public R upVideoU(ParameterVo vo){

        if (vo.getSps() != null) {
            OrderEntity orders=orderService.getById(vo.getDdid());
            if (vo.getZtdm().equals(orders.getZt())){

                boolean fileUpload = false;
                List<String> fileNames = new ArrayList<>();
                for (int i=0;i<vo.getSps().length;i++) {
                    String fname=vo.getSps()[i].getOriginalFilename();
                    String fileName=vo.getDdid()+"-"+System.currentTimeMillis()+fname.substring(fname.lastIndexOf("."), fname.length());;
                    String fileUrl=saveFile.getProfile();
                    fileUpload = ServiceSaveFile.singleFileUpload(vo.getSps()[i], fileName, fileUrl + "/voucher-save/");
                    fileNames.add(fileName);
                    if (!fileUpload){
                        continue;
                    }
                }
                if (fileUpload) {
                    OrderEntity order=new OrderEntity();
                    order.setDdid(vo.getDdid());
                    if (vo.getZtdm()==OrderConstant.StatusEnum.M_NO_P_USER_UP_VIDEO.getCode()){
                        order.setZt(OrderConstant.StatusEnum.M_NO_P_MERCHANT_UP_VIDEO.getCode());
                    }
                    if (vo.getZtdm()==OrderConstant.StatusEnum.U_NO_C_USER_UP_VIDEO.getCode()){
                        order.setZt(OrderConstant.StatusEnum.U_NO_C_MERCHANT_UP_VIDEO.getCode());
                    }
                    if (vo.getZtdm()==OrderConstant.StatusEnum.M_NO_C_USER_UP_VIDEO.getCode()){
                        order.setZt(OrderConstant.StatusEnum.M_NO_C_MERCHANT_UP_VIDEO.getCode());
                    }
                    if (vo.getZtdm()==OrderConstant.StatusEnum.U_NO_P_USER_UP_VIDEO.getCode()){
                        order.setZt(OrderConstant.StatusEnum.U_NO_P_MERCHANT_UP_VIDEO.getCode());
                    }
                    order.setZdsx(new Date(new Date().getTime()+ OrderConstant.ORDER_STATUS_TIME_LIMIT));
                    order.setGxsj(new Date());
                    orderService.updateById(order);

                    OrderChangeEntity changeEntity=new OrderChangeEntity();
                    changeEntity.setSpid(orders.getSpid());
                    changeEntity.setDdid(orders.getDdid());
                    changeEntity.setDdzt(order.getZt());
                    changeEntity.setJs(OrderConstant.JsEnum.USER.getCode());
                    changeService.save(changeEntity);

                    VoucherEntity voucherEntity=new VoucherEntity();
                    voucherEntity.setDdid(orders.getDdid());
                    voucherEntity.setDdzt(orders.getZt());
                    voucherEntity.setJs(OrderConstant.JsEnum.USER.getCode());
                    fileNames.forEach(fileName->{
                        voucherEntity.setSppz(fileName);
                        voucherService.save(voucherEntity);
                    });

                    return R.ok();
                } else {
                    return R.error("视频保存失败，请重新尝试！");
                }
            } else {
                return R.error("异常操作！");
            }
        } else {
            return R.error("视频不能为空！");
        }
    }


    /**
     * 币商上传图片凭证（upImg）
     */
    @RequestMapping("/merchant/upImg")
    @Transactional(rollbackFor = Exception.class)
    public R upImgM(ParameterVo vo){

        if (vo.getImgs() != null) {
            OrderEntity orders=orderService.getById(vo.getDdid());
            if (vo.getZtdm().equals(orders.getZt())){
                boolean fileUpload = false;
                List<String> fileNames = new ArrayList<>();
                for (int i=0;i<vo.getImgs().length;i++) {
                    String fname=vo.getImgs()[i].getOriginalFilename();
                    String fileName=vo.getDdid()+"-"+System.currentTimeMillis()+fname.substring(fname.lastIndexOf("."), fname.length());;
                    String fileUrl=saveFile.getProfile();
                    fileUpload = ServiceSaveFile.singleFileUpload(vo.getImgs()[i], fileName, fileUrl + "/voucher-save/");
                    fileNames.add(fileName);
                    if (!fileUpload){
                        continue;
                    }
                }
                if (fileUpload) {
                    OrderEntity order=new OrderEntity();
                    order.setDdid(vo.getDdid());
                    if (vo.getZtdm()==OrderConstant.StatusEnum.M_NO_P_MERCHANT_UP_IMG.getCode()){
                        order.setZt(OrderConstant.StatusEnum.M_NO_P_USER_UP_VIDEO.getCode());
                    }
                    if (vo.getZtdm()==OrderConstant.StatusEnum.U_NO_C_MERCHANT_UP_IMG.getCode()){
                        order.setZt(OrderConstant.StatusEnum.U_NO_C_USER_UP_VIDEO.getCode());
                    }
                    if (vo.getZtdm()==OrderConstant.StatusEnum.M_NO_C_MERCHANT_UP_IMG.getCode()){
                        order.setZt(OrderConstant.StatusEnum.M_NO_C_USER_UP_VIDEO.getCode());
                    }
                    if (vo.getZtdm()==OrderConstant.StatusEnum.U_NO_P_MERCHANT_UP_IMG.getCode()){
                        order.setZt(OrderConstant.StatusEnum.U_NO_P_USER_UP_VIDEO.getCode());
                    }
                    order.setZdsx(new Date(new Date().getTime()+ OrderConstant.ORDER_STATUS_TIME_LIMIT));
                    order.setGxsj(new Date());
                    orderService.updateById(order);

                    OrderChangeEntity changeEntity=new OrderChangeEntity();
//                    order=orderService.getById(vo.getDdid());
                    changeEntity.setSpid(orders.getSpid());
                    changeEntity.setDdid(orders.getDdid());
                    changeEntity.setDdzt(order.getZt());
                    changeEntity.setJs(OrderConstant.JsEnum.C_M.getCode());
                    changeService.save(changeEntity);

                    VoucherEntity voucherEntity=new VoucherEntity();
                    voucherEntity.setDdid(orders.getDdid());
                    voucherEntity.setDdzt(orders.getZt());
                    voucherEntity.setJs(OrderConstant.JsEnum.C_M.getCode());
                    fileNames.forEach(fileName->{
                        voucherEntity.setTppz(fileName);
                        voucherService.save(voucherEntity);
                    });
                    return R.ok();
                } else {
                    return R.error("图片保存失败，请重新尝试！");
                }
            } else {
                return R.error("异常操作！");
            }
        } else {
            return R.error("图片不能为空！");
        }
    }


    /**
     * 币商上传视频凭证 (upVideo)
     */
    @RequestMapping("/merchant/upVideo")
    @Transactional(rollbackFor = Exception.class)
    public R upVideoM(ParameterVo vo){

        if (vo.getSps() != null) {
            OrderEntity orders=orderService.getById(vo.getDdid());
            if (vo.getZtdm().equals(orders.getZt())){

                boolean fileUpload = false;
                List<String> fileNames = new ArrayList<>();
                for (int i=0;i<vo.getSps().length;i++) {
                    String fname=vo.getSps()[i].getOriginalFilename();
                    String fileName=vo.getDdid()+"-"+System.currentTimeMillis()+fname.substring(fname.lastIndexOf("."), fname.length());;
                    String fileUrl=saveFile.getProfile();
                    fileUpload = ServiceSaveFile.singleFileUpload(vo.getSps()[i], fileName, fileUrl + "/voucher-save/");
                    fileNames.add(fileName);
                    if (!fileUpload){
                        continue;
                    }
                }
                if (fileUpload) {

                    OrderEntity order=new OrderEntity();
                    order.setDdid(vo.getDdid());
                    order.setZt(OrderConstant.StatusEnum.O_E_W_F_P_P.getCode());
                    order.setShzt(0);
                    order.setZdsx(null);
                    order.setGxsj(new Date());
                    orderService.updateById(order);

                    OrderChangeEntity changeEntity=new OrderChangeEntity();
//                    order=orderService.getById(vo.getDdid());
                    changeEntity.setSpid(orders.getSpid());
                    changeEntity.setDdid(orders.getDdid());
                    changeEntity.setDdzt(order.getZt());
                    changeEntity.setJs(OrderConstant.JsEnum.C_M.getCode());
                    changeService.save(changeEntity);

                    VoucherEntity voucherEntity=new VoucherEntity();
                    voucherEntity.setDdid(orders.getDdid());
                    voucherEntity.setDdzt(orders.getZt());
                    voucherEntity.setJs(OrderConstant.JsEnum.C_M.getCode());
                    fileNames.forEach(fileName->{
                        voucherEntity.setSppz(fileName);
                        voucherService.save(voucherEntity);
                    });

                    return R.ok();
                } else {
                    return R.error("视频保存失败，请重新尝试！");
                }
            } else {
                return R.error("异常操作！");
            }
        } else {
            return R.error("视频不能为空！");
        }
    }


    /**
     * 门户审核判定
     */
    @RequestMapping("/update")
    @Transactional(rollbackFor = Exception.class)
    public R update(@RequestBody OrderEntity orders){
        System.out.println(orders);
        if (orders.getShid()==101||orders.getShid()==201||orders.getShid()==104||orders.getShid()==204){
            orders.setZt(OrderConstant.StatusEnum.EXCEPTION.getCode());
        }
        if (orders.getShid()==102||orders.getShid()==202){
            orders.setZt(OrderConstant.StatusEnum.FAIL.getCode());
        }
        if (orders.getShid()==103||orders.getShid()==203){
            orders.setZt(OrderConstant.StatusEnum.TRANSACTION_COMPLETED.getCode());
        }
        orders.setShzt(1);
        orders.setZdsx(null);
        orders.setGxsj(new Date());
		orderService.updateById(orders);

        OrderChangeEntity changeEntity=new OrderChangeEntity();
        OrderEntity order=orderService.getById(orders.getDdid());
        changeEntity.setSpid(order.getSpid());
        changeEntity.setDdid(orders.getDdid());
        changeEntity.setDdzt(orders.getZt());
        changeService.save(changeEntity);

//        OrderEntity order=orderService.getById(orders.getDdid());
        //商品数量变更
        CommodityEntity spsl = commodityService.getById(order.getSpid());
        CommodityEntity commodityEntity =new CommodityEntity();
        commodityEntity.setSpid(spsl.getSpid());
        commodityEntity.setGxsj(new Date());

        //保证金变更
        DepositEntity bzjid =  depositService.getOne(new QueryWrapper<DepositEntity>().eq("bsid",order.getBsdz()).eq("fbid",order.getFbid()));
        DepositEntity depositEntity = new DepositEntity();
        depositEntity.setBzjid(bzjid.getBzjid());
        depositEntity.setXgsj(new Date());
        DepositHistoryEntity historyEntity =new DepositHistoryEntity();
        historyEntity.setBzjid(bzjid.getBzjid());
        historyEntity.setBsid(bzjid.getBsid());
        historyEntity.setFbid(bzjid.getFbid());

        if (orders.getShid()==102||orders.getShid()==202) {

            commodityEntity.setSdsl(MathUtils.sub(spsl.getSdsl(),order.getSl()));// spsl.getSdsl()-order.getSl());
            commodityEntity.setKcsl(MathUtils.add(spsl.getKcsl(),order.getSl()));// spsl.getKcsl()+order.getSl());

            depositEntity.setSded(MathUtils.sub(bzjid.getSded(),order.getJe()));// bzjid.getSded()-order.getJe());
            depositEntity.setKsyed(MathUtils.add(bzjid.getKsyed(),order.getJe()));// bzjid.getKsyed()+order.getJe());

            historyEntity.setJsed(order.getJe());

        } else if (orders.getShid()==103||orders.getShid()==203) {

            commodityEntity.setSdsl(MathUtils.add(bzjid.getKsyed(),order.getJe()));// spsl.getSdsl()-order.getSl());
            commodityEntity.setCjsl(MathUtils.add(bzjid.getKsyed(),order.getJe()));// spsl.getCjsl()+order.getSl());

            //保证金变更
            depositEntity.setSded(MathUtils.sub(bzjid.getSded(),order.getJe()));// bzjid.getSded() - order.getJe());
            depositEntity.setKsyed(MathUtils.add(bzjid.getKsyed(),MathUtils.sub(order.getJe(),order.getSxf())));// bzjid.getKsyed() + order.getJe() - order.getSxf());
            depositEntity.setKcsxf(MathUtils.add(bzjid.getKcsxf(),order.getSxf()));// bzjid.getKcsxf() + order.getSxf());

            historyEntity.setKcsxf(order.getSxf());
            historyEntity.setJsed(order.getJe());
        } else {
            //商品数量变更
            commodityEntity.setSdsl(MathUtils.sub(spsl.getSdsl(),order.getSl()));// spsl.getSdsl()-order.getSl());
            commodityEntity.setCjsl(MathUtils.add(spsl.getCjsl(),order.getSl()));// spsl.getCjsl()+order.getSl());
            //保证金变更
            Double kced = MathUtils.add(order.getJe(),MathUtils.mul(order.getJe(),OrderConstant.DEDUCTION_BASE));//order.getJe() + order.getJe()*OrderConstant.DEDUCTION_BASE;
            depositEntity.setSded(MathUtils.sub(bzjid.getSded(),order.getJe()));// bzjid.getSded() - order.getJe());
            depositEntity.setKced(MathUtils.add(bzjid.getKced(),kced));// bzjid.getKced() + kced);
            depositEntity.setKsyed(MathUtils.sub(bzjid.getKsyed(),MathUtils.sub(kced,order.getSxf())));// bzjid.getKsyed() - kced - order.getSxf());
            depositEntity.setKcsxf(MathUtils.add(bzjid.getKcsxf(),order.getSxf()));// bzjid.getKcsxf() + order.getSxf());
            depositEntity.setKcwgfy(MathUtils.add(bzjid.getKcwgfy(),MathUtils.mul(order.getJe(),OrderConstant.DEDUCTIONS_BASE))); // bzjid.getKcwgfy() + order.getJe()*OrderConstant.DEDUCTIONS_BASE);

            historyEntity.setKcsxf(order.getSxf());
            historyEntity.setKced(kced);
            historyEntity.setKcyy(order.getShyj());
        }

        commodityEntity.setGxsj(new Date());
        commodityService.updateById(commodityEntity);

        depositEntity.setXgsj(new Date());
        depositService.updateById(depositEntity);

        depositHistoryService.save(historyEntity);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		orderService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /*定时任务*/

    //下单时间限制任务
//    public void orderMonitoring(OrderChangeService changeService) {
//        this.changeService = changeService;
//    }
//    //订单状态变更时间限制任务
//    public void statusMonitoring(OrderChangeService changeService) {
//        this.changeService = changeService;
//    }
}


