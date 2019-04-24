package com.chuanfangn.sell.converter;

import com.chuanfangn.sell.dto.CartDTO;
import com.chuanfangn.sell.entity.OrderDetail;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 购物车集合->商品详情集合转换器
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-04-24 23:24
 * @version: 1.0
 **/
public class CartDtoList2OrderDetailListConverter {
    public static OrderDetail converter(CartDTO cartDTO){
        OrderDetail orderDetail = new OrderDetail();
        BeanUtils.copyProperties(cartDTO,orderDetail);
        return orderDetail;
    }
    public static List<OrderDetail> converter(List<CartDTO> cartDTOS){
        List<OrderDetail> orderDetails = cartDTOS.stream().map(e -> converter(e)).collect(Collectors.toList());
        return orderDetails;
    }
}
