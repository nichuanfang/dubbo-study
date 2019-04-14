package com.chuanfangn.sell.repository;

import com.chuanfangn.sell.entity.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Test
    public void findAllByOrderId(){
        OrderDetail allByOrderId = orderDetailRepository.findByOrderId("1");
        Assert.assertNotNull(allByOrderId);
    }
}
