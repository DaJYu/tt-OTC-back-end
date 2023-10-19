package com.ydj.ttswap.service.impl;

import com.ydj.ttswap.constant.OrderConstant;
import com.ydj.ttswap.entity.*;
import com.ydj.ttswap.service.*;
import com.ydj.ttswap.utils.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ydj.ttswap.utils.PageUtils;
import com.ydj.ttswap.utils.Query;

import com.ydj.ttswap.mapper.OrderMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity> implements OrderService {

    @Autowired
    OrderMapper mapper;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderChangeService changeService;
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private DepositService depositService;
    @Autowired
    private DepositHistoryService depositHistoryService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<OrderEntity> wrapper = new QueryWrapper<>();
        String id= (String) params.get("status");
        if (!StringUtils.isEmpty(id)){
            wrapper.eq("zt",id);
        }
        wrapper.orderByDesc("cjsj");
        IPage<OrderEntity> page = this.page(
                new Query<OrderEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPages(String js, Map<String, Object> params) {
        QueryWrapper<OrderEntity> wrapper = new QueryWrapper<>();
        if (js.equals("user")){
            wrapper.eq("yhdz",params.get("qbid"));
        }else {
            wrapper.eq("bsdz",params.get("qbid"));
        }
        String id= (String) params.get("status");
        if (!StringUtils.isEmpty(id)){
            if(params.get("status").equals("1111")) {
                wrapper.notIn("zt",OrderConstant.StatusEnum.PLACE_ORDER.getCode(),OrderConstant.StatusEnum.CANCELLATION_OF_ORDER.getCode(),OrderConstant.StatusEnum.TRANSACTION_COMPLETED.getCode(),OrderConstant.StatusEnum.EXCEPTION.getCode(),OrderConstant.StatusEnum.FAIL.getCode());
            } else if (params.get("status").equals("1000")) {} else  {
                wrapper.eq("zt",id);
            }
        }
        else {
            wrapper.notIn("zt",OrderConstant.StatusEnum.PLACE_ORDER.getCode(),OrderConstant.StatusEnum.CANCELLATION_OF_ORDER.getCode(),OrderConstant.StatusEnum.TRANSACTION_COMPLETED.getCode(),OrderConstant.StatusEnum.EXCEPTION.getCode(),OrderConstant.StatusEnum.FAIL.getCode());
        }
        wrapper.orderByDesc("gxsj");
        IPage<OrderEntity> page = this.page(
                new Query<OrderEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

    @Override
    public List<OrderEntity> lists() {
        QueryWrapper<OrderEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("zt",OrderConstant.StatusEnum.PLACE_ORDER.getCode());
        wrapper.isNotNull("zdsx");
         List<OrderEntity> list= mapper.selectList(wrapper);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancel(OrderEntity order, String yj) {

        OrderEntity orders=new OrderEntity();
        orders.setDdid(order.getDdid());
        orders.setZt(OrderConstant.StatusEnum.CANCELLATION_OF_ORDER.getCode());
        orders.setZdsx(null);
        orders.setGxsj(new Date());
        orderService.updateById(orders);

        OrderChangeEntity changeEntity=new OrderChangeEntity();
        changeEntity.setDdid(order.getDdid());
        changeEntity.setSpid(order.getSpid());
        changeEntity.setJs(OrderConstant.JsEnum.USER.getCode());
        changeEntity.setDdzt(OrderConstant.StatusEnum.CANCELLATION_OF_ORDER.getCode());
        changeEntity.setZtbgyy(yj);
        changeService.save(changeEntity);

//        order=orderService.getById(vo.getDdid());
        CommodityEntity spsl = commodityService.getById(order.getSpid());
        //商品数量变更
        CommodityEntity commodityEntity =new CommodityEntity();
        commodityEntity.setSpid(spsl.getSpid());
        commodityEntity.setSdsl(MathUtils.sub(spsl.getSdsl(),order.getSl()));//spsl.getSdsl()-order.getSl());
        commodityEntity.setKcsl(MathUtils.add(spsl.getKcsl(),order.getSl()));//spsl.getKcsl()+order.getSl());
        commodityEntity.setGxsj(new Date());
        commodityService.updateById(commodityEntity);
        //保证金变更
        DepositEntity bzjid =  depositService.getOne(new QueryWrapper<DepositEntity>().eq("bsid",order.getBsdz()).eq("fbid",order.getFbid()));
        DepositEntity depositEntity = new DepositEntity();
        depositEntity.setBzjid(bzjid.getBzjid());
        depositEntity.setSded(MathUtils.sub(bzjid.getSded(),order.getJe()));//bzjid.getSded()-order.getJe());
        depositEntity.setKsyed(MathUtils.add(bzjid.getKsyed(),order.getJe()));//bzjid.getKsyed()+order.getJe());
        depositEntity.setXgsj(new Date());
        depositService.updateById(depositEntity);

        DepositHistoryEntity historyEntity =new DepositHistoryEntity();
        historyEntity.setBzjid(bzjid.getBzjid());
        historyEntity.setBsid(bzjid.getBsid());
        historyEntity.setFbid(bzjid.getFbid());
        historyEntity.setJsed(order.getJe());
        depositHistoryService.save(historyEntity);
    }

    @Override
    public List<OrderEntity> statusList() {
        QueryWrapper<OrderEntity> wrapper = new QueryWrapper<>();
        wrapper.ne("zt",OrderConstant.StatusEnum.PLACE_ORDER.getCode());
        wrapper.isNotNull("zdsx");
        List<OrderEntity> list= mapper.selectList(wrapper);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void statusMonitoring(OrderEntity i) {
        if (i.getZt()==OrderConstant.StatusEnum.M_C_P.getCode()
                || i.getZt()==OrderConstant.StatusEnum.M_C_C.getCode()
                || i.getZt()==OrderConstant.StatusEnum.M_NO_P_MERCHANT_UP_IMG.getCode()
                || i.getZt()==OrderConstant.StatusEnum.U_NO_C_MERCHANT_UP_IMG.getCode()
                || i.getZt()==OrderConstant.StatusEnum.M_NO_C_MERCHANT_UP_IMG.getCode()
                || i.getZt()==OrderConstant.StatusEnum.U_NO_P_MERCHANT_UP_IMG.getCode()
                ){
            statusChange(i.getDdid(),OrderConstant.StatusEnum.O_E_W_F_P_P.getCode(),"币商确认超时，系统交由门户处理中");
        }
        if (i.getZt()==OrderConstant.StatusEnum.M_NO_P_USER_UP_IMG.getCode()
                || i.getZt()==OrderConstant.StatusEnum.M_NO_P_USER_UP_VIDEO.getCode()
                || i.getZt()==OrderConstant.StatusEnum.M_NO_C_USER_UP_IMG.getCode()
                || i.getZt()==OrderConstant.StatusEnum.M_NO_C_USER_UP_VIDEO.getCode()
                ) {
            cancel(i,"用户确认超时，默认自动取消订单！");
        }
        if (i.getZt()==OrderConstant.StatusEnum.U_C_C.getCode()
                || i.getZt()==OrderConstant.StatusEnum.U_C_P.getCode()
                || i.getZt()==OrderConstant.StatusEnum.U_NO_C_USER_UP_IMG.getCode()
                || i.getZt()==OrderConstant.StatusEnum.U_NO_C_USER_UP_VIDEO.getCode()
                || i.getZt()==OrderConstant.StatusEnum.U_NO_P_USER_UP_IMG.getCode()
                || i.getZt()==OrderConstant.StatusEnum.U_NO_P_USER_UP_VIDEO.getCode()
                ) {
            statusChange(i.getDdid(),OrderConstant.StatusEnum.TRANSACTION_COMPLETED.getCode(),"用户确认超时，默认自动完成订单！");
            collectionU(i);
        }
    }

    private void statusChange(Long ddid,Integer zt,String bgyj){
        OrderEntity order=new OrderEntity();
        if (zt==OrderConstant.StatusEnum.O_E_W_F_P_P.getCode()){
            order.setShzt(0);
        }
        order.setDdid(ddid);
        order.setZt(zt);
        order.setZdsx(null);
        order.setGxsj(new Date());
        mapper.updateById(order);

        OrderChangeEntity changeEntity=new OrderChangeEntity();
        changeEntity.setDdid(ddid);
        changeEntity.setDdzt(zt);
        changeEntity.setZtbgyy(bgyj);
        changeService.save(changeEntity);
    }

    /*完成订单，商品、保证金变更*/
    public void collectionU(OrderEntity order){

        CommodityEntity spsl = commodityService.getById(order.getSpid());
        DepositEntity bzjid =  depositService.getOne(new QueryWrapper<DepositEntity>().eq("bsid",order.getBsdz()).eq("fbid",order.getFbid()));
        //商品数量变更
        CommodityEntity commodityEntity =new CommodityEntity();
        commodityEntity.setSpid(spsl.getSpid());
        commodityEntity.setSdsl(MathUtils.sub(spsl.getSdsl(),order.getSl()));//spsl.getSdsl() - order.getSl());
        commodityEntity.setCjsl(MathUtils.add(spsl.getCjsl(),order.getSl()));//spsl.getCjsl() + order.getSl());
        commodityEntity.setGxsj(new Date());
        commodityService.updateById(commodityEntity);
        //保证金变更
        DepositEntity depositEntity = new DepositEntity();
        depositEntity.setBzjid(bzjid.getBzjid());
        depositEntity.setSded(MathUtils.sub(bzjid.getSded(),order.getJe()));//bzjid.getSded() - order.getJe());
        depositEntity.setKsyed(MathUtils.add(bzjid.getKsyed(),MathUtils.sub(order.getJe(),order.getSxf())));//bzjid.getKsyed() + order.getJe() - order.getSxf());
        depositEntity.setKcsxf(MathUtils.add(bzjid.getKcsxf(),order.getSxf()));//bzjid.getKcsxf() + order.getSxf());
        depositEntity.setXgsj(new Date());
        depositService.updateById(depositEntity);

        DepositHistoryEntity historyEntity =new DepositHistoryEntity();
        historyEntity.setBzjid(bzjid.getBzjid());
        historyEntity.setBsid(bzjid.getBsid());
        historyEntity.setFbid(bzjid.getFbid());
        historyEntity.setJsed(order.getJe());
        historyEntity.setKcsxf(order.getSxf());
        depositHistoryService.save(historyEntity);
    }
}