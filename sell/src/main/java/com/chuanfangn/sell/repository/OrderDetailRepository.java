package com.chuanfangn.sell.repository;

import com.chuanfangn.sell.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-04-13 03:05
 * @version:
 **/
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer> {
    /**
     * 方法功能:
     * @param orderId
     * @author f18326186224@gmail.com
     * @creatDate  2019/4/13 3:30
     * @return com.chuanfangn.sell.entity.OrderDetail
     */
    OrderDetail findByOrderId(String orderId);
}
