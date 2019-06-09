package com.chuanfangn.sell.service;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-05-15 15:27
 * @version:
 **/
public interface PayService {


    /**
     * 方法功能: 异步通知service
     * @param notifyDate
     * @author f18326186224@gmail.com
     * @creatDate  2019/5/15 15:35
     * @return com.lly835.bestpay.model.PayResponse
     */
    PayResponse notify(String notifyDate);
    /**
     * 方法功能: 退款
     * @param orderId
     * @author f18326186224@gmail.com
     * @creatDate  2019/5/18 22:35
     * @return com.lly835.bestpay.model.RefundResponse
     */
    RefundResponse refund(String orderId);
}
