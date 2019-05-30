package com.chuanfangn.sell.repository;
import java.math.BigDecimal;
import java.sql.Timestamp;

import com.chuanfangn.sell.entity.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderDetailRepositoryTest {
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Test
    public void test(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1231");
        orderDetail.setOrderId("4444");
        orderDetail.setProductId("1");
        orderDetail.setProductName("dsf");
        orderDetail.setProductPrice(2.0);
        orderDetail.setProductQuantity(1);
        orderDetail.setProductIcon("asd");
        orderDetail.setCreateTime(new Timestamp(new java.util.Date().getTime()));
        orderDetail.setUpdateTime(new Timestamp(new java.util.Date().getTime()));
        OrderDetail save = orderDetailRepository.save(orderDetail);
        log.info(save.toString());
    }
    @Test
    public void findAllByOrderId(){
        List<OrderDetail> allByOrderId = orderDetailRepository.findAllByOrderId("498170_1556604466090");
        allByOrderId.forEach(orderDetail -> {
            System.out.println(orderDetail);
        });
    }
}
