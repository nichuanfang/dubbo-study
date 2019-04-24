package com.chuanfangn.sell.converter;

import com.chuanfangn.sell.dto.OrderDTO;
import com.chuanfangn.sell.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: orderDto -> orderMaster转换器
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-04-20 22:14
 * @version: 1.0
 **/
public class OrderDto2OrderMasterConverter {
    public static OrderMaster converter(OrderDTO orderDTO){
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        return orderMaster;
    }
    public static List<OrderMaster> converter(List<OrderDTO> orderDTOList){
        List<OrderMaster> collect = orderDTOList.stream().map(orderDTO -> converter(orderDTO)).collect(Collectors.toList());
        return collect;
    }
}
