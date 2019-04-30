package com.chuanfangn.sell.repository;

import com.chuanfangn.sell.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-04-15 00:33
 * @version:
 **/
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
    /**
     * 方法功能: 通过订单id查询
     * @param orderId
     * @author f18326186224@gmail.com
     * @creatDate  2019/4/30 17:08
     * @return java.util.List<com.chuanfangn.sell.entity.OrderDetail>
     */
    List<OrderDetail> findAllByOrderId(String orderId);
}
