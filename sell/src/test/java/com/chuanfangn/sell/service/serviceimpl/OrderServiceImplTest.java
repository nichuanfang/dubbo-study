package com.chuanfangn.sell.service.serviceimpl;


import com.chuanfangn.sell.dto.OrderDTO;
import com.chuanfangn.sell.entity.OrderDetail;
import com.chuanfangn.sell.entity.OrderMaster;
import com.chuanfangn.sell.enums.OrderStatusEnums;
import com.chuanfangn.sell.enums.PayStatusEnums;
import com.chuanfangn.sell.repository.OrderMasterRepository;
import com.chuanfangn.sell.utils.IdGenerateUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class OrderServiceImplTest {
    private final String OPENID = "54675643";
    private final String ORDERID = "760007_1555269702092";
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    OrderServiceImpl orderService;

    @Test
    public void deCreaseOrderTest(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress("浙江省杭州市");
        orderDTO.setBuyerName("倪传方");
        orderDTO.setBuyerOpenid(OPENID);
        orderDTO.setBuyerPhone("18326186224");
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId(IdGenerateUtil.getId());
        orderDetail.setProductId("1");
        orderDetail.setProductQuantity(2);
        orderDetails.add(orderDetail);
        orderDTO.setOrderDetails(orderDetails);
        OrderDTO orderDTO1 = orderService.create(orderDTO);
        log.info(orderDTO1.toString());
    }
    @Test
    public void finishTest(){
        OrderDTO orderDTO = new OrderDTO();
        OrderMaster orderMaster = orderMasterRepository.findById(ORDERID).get();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        OrderDTO finish = orderService.finish(orderDTO);
        Assert.assertNotEquals(OrderStatusEnums.NEW.getCode(),finish.getOrderStatus());
    }

    @Test
    public void paidTest(){
        OrderDTO orderDTO = new OrderDTO();
        OrderMaster orderMaster = orderMasterRepository.findById(ORDERID).get();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        OrderDTO paid = orderService.paid(orderDTO);
        Assert.assertNotEquals(PayStatusEnums.WAIT.getCode(),paid.getPayStatus());
    }
}
