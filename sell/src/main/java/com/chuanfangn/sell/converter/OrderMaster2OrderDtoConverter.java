package com.chuanfangn.sell.converter;

import com.chuanfangn.sell.dto.OrderDTO;
import com.chuanfangn.sell.entity.OrderMaster;
import com.chuanfangn.sell.repository.OrderDetailRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-05-23 15:22
 * @version:
 **/
public class OrderMaster2OrderDtoConverter {

    public static OrderDTO getOrderDto(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderAmount(BigDecimal.valueOf(orderMaster.getOrderAmount()));
        return orderDTO;
    }

    public static List<OrderDTO> getOrderDtoList(List<OrderMaster> orderMasters){
        List<OrderDTO> orderDTOS = orderMasters.stream().map(e -> {
            return getOrderDto(e);
        }).collect(Collectors.toList());
        return orderDTOS;
    }

}
