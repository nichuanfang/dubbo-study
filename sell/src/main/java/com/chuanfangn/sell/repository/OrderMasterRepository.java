package com.chuanfangn.sell.repository;

import com.chuanfangn.sell.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-04-15 00:31
 * @version:
 **/
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String>{
    /**
     * 方法功能:
     * @param openid
     * @param pageable
     * @author f18326186224@gmail.com
     * @creatDate  2019/4/30 15:29
     * @return java.util.List<com.chuanfangn.sell.entity.OrderMaster>
     */
    Page<OrderMaster> findAllByBuyerOpenid(String openid, Pageable pageable);
}
