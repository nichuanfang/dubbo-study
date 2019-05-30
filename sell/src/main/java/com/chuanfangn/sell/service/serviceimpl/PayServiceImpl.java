package com.chuanfangn.sell.service.serviceimpl;

import com.chuanfangn.sell.dto.OrderDTO;
import com.chuanfangn.sell.enums.ResultEnums;
import com.chuanfangn.sell.exception.ProductException;
import com.chuanfangn.sell.service.OrderService;
import com.chuanfangn.sell.service.PayService;
import com.chuanfangn.sell.utils.MathUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.BestPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-05-15 15:28
 * @version:
 **/
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class PayServiceImpl implements PayService {

    @Autowired
    BestPayService bestPayService;
    @Autowired
    OrderService orderService;
    @Override
    public PayResponse notify(String notifyDate) {
        PayResponse payResponse = bestPayService.asyncNotify(notifyDate);
        OrderDTO one = orderService.findOne(bestPayService.asyncNotify(notifyDate).getOrderId());
        if(one==null) {
            throw new ProductException(ResultEnums.PRODUCT_NOT_EXIST);
        }
        if(!MathUtil.compare(payResponse.getOrderAmount(),one.getOrderAmount().doubleValue())){
            log.error("[支付订单]失败,payresponse={}",payResponse);
            throw new ProductException(ResultEnums.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
        }
        //支付订单
        OrderDTO paid = orderService.paid(one);
        return payResponse;
    }

    @Override
    public RefundResponse refund(String orderId) {
        RefundRequest refundRequest = new RefundRequest();
        OrderDTO one = orderService.findOne(orderId);
        refundRequest.setOrderId(orderId);
        refundRequest.setOrderAmount(one.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        RefundResponse refund = bestPayService.refund(refundRequest);
        return refund;
    }
}
