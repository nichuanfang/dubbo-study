package com.chuanfangn.sell.service;

import com.chuanfangn.sell.dto.OrderDTO;

import java.util.List;

/**
 * @description:订单业务逻辑接口
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-04-14 22:53
 * @version:
 **/
public interface OrderService {
    /**
     * 方法功能: 创建订单: 1.商品数量+价格 2.计算总价格 3.判断库存是否足够 4.将数据写进数据库 5.下单成功,减库存
     *
     * @param orderDTO
     * @return com.chuanfangn.sell.dto.OrderDTO
     * @author f18326186224@gmail.com
     * @creatDate 2019/4/14 22:56
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 方法功能: 完结订单 1.判断订单状态 2.修改订单状态
     *
     * @param
     * @return com.chuanfangn.sell.dto.OrderDTO
     * @author f18326186224@gmail.com
     * @creatDate 2019/4/20 21:27
     */
    OrderDTO finish(OrderDTO orderDTO);

    /**
     * 方法功能:支付订单
     *
     * @param orderDTO
     * @return com.chuanfangn.sell.dto.OrderDTO
     * @author f18326186224@gmail.com
     * @creatDate 2019/4/24 20:18
     */
    OrderDTO paid(OrderDTO orderDTO);

    /**
     * 方法功能:查询订单
     *
     * @param buyerOpenid
     * @param page
     * @param size
     * @return java.util.List<com.chuanfangn.sell.dto.OrderDTO>
     * @author f18326186224@gmail.com
     * @creatDate 2019/4/30 15:32
     */
    List<OrderDTO> getOrderDtoList(String buyerOpenid, Integer page, Integer size);

    //取消订单
    //TODO
    //完结订单
    //TODO
}
