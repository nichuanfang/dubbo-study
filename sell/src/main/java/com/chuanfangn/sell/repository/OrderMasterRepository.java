package com.chuanfangn.sell.repository;

import com.chuanfangn.sell.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-04-13 03:04
 * @version:
 **/
public interface OrderMasterRepository extends JpaRepository<OrderMaster,Integer> {
    /**
     * 方法功能:
     * @param openid
     * @param pageable
     * @author f18326186224@gmail.com
     * @creatDate  2019/4/13 3:34
     * @return org.springframework.data.domain.Page<com.chuanfangn.sell.entity.OrderMaster>
     */
    Page<OrderMaster> findAllByBuyerOpenid(String openid, Pageable pageable);
}
