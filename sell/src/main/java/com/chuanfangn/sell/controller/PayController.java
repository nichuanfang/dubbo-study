package com.chuanfangn.sell.controller;

import com.chuanfangn.sell.entity.OrderMaster;
import com.chuanfangn.sell.propertyclasses.PayProperties;
import com.chuanfangn.sell.repository.OrderMasterRepository;
import com.chuanfangn.sell.service.OrderService;
import com.chuanfangn.sell.service.PayService;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-05-06 11:18
 * @version:
 **/
@Controller
@RequestMapping("/pay")
@Slf4j
public class PayController {
    @Autowired
    BestPayService bestPayService;
    @Autowired
    OrderMasterRepository orderMasterRepository;
    @Autowired
    PayProperties payProperties;
    @Autowired
    PayService payService;

    @Autowired
    OrderService orderService;

    @GetMapping("/create")
    public String create(@RequestParam("orderId") String orderId, ModelMap map) {
        PayRequest payRequest = new PayRequest();
        OrderMaster orderMaster = orderMasterRepository.findById(orderId).orElse(null);
        payRequest.setOpenid(orderMaster.getBuyerOpenid());
        payRequest.setOrderAmount(orderMaster.getOrderAmount());
        payRequest.setOrderId(orderMaster.getOrderId());
        payRequest.setOrderName("微信公众账号支付订单");
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        PayResponse pay = bestPayService.pay(payRequest);
        JsonUtil.toJson(pay);
        log.info("[微信支付]payResponse=" + pay);
        map.put("pay", pay);

        return "create";
    }


    /**
     * 方法功能:@Requestbody获得请求体中的参数,限定post请求,不写该注解获取不到参数!; @requestparam获得key-value的数据,get请求,可省略
     *
     * @param notifyDate
     * @return java.lang.String
     * @author f18326186224@gmail.com
     * @creatDate 2019/5/16 13:22
     */
    @PostMapping("/notify")
    public String notify(@RequestBody String notifyDate) {
        //1.验证签名
        //2. 验证支付状态
        //3.验证金额
        //4.验证支付人和订单人是否一致
        //5.修改订单状态
        payService.notify(notifyDate);
        return "notify";
    }


}
