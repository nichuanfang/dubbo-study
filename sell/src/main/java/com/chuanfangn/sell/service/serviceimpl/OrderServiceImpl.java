package com.chuanfangn.sell.service.serviceimpl;

import com.chuanfangn.sell.converter.OrderDto2OrderMasterConverter;
import com.chuanfangn.sell.dto.CartDTO;
import com.chuanfangn.sell.dto.OrderDTO;
import com.chuanfangn.sell.entity.OrderDetail;
import com.chuanfangn.sell.entity.OrderMaster;
import com.chuanfangn.sell.entity.ProductInfo;
import com.chuanfangn.sell.enums.OrderStatusEnums;
import com.chuanfangn.sell.enums.PayStatusEnums;
import com.chuanfangn.sell.enums.ResultEnums;
import com.chuanfangn.sell.exception.ProductException;
import com.chuanfangn.sell.repository.OrderDetailRepository;
import com.chuanfangn.sell.repository.OrderMasterRepository;
import com.chuanfangn.sell.service.OrderService;
import com.chuanfangn.sell.service.ProductInfoService;
import com.chuanfangn.sell.utils.IdGenerateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:订单时实现类
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-04-14 23:40
 * @version:
 **/
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class OrderServiceImpl implements OrderService {
    /**
     * 商品总价
     */
    private BigDecimal totalAccount = new BigDecimal(BigInteger.ZERO);

    @Autowired
    ProductInfoService productInfoService;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    OrderMasterRepository orderMasterRepository;

    @Override
    public synchronized OrderDTO create(OrderDTO orderDTO) {
        String id = IdGenerateUtil.getId();
        List<OrderDetail> orderDetails = orderDTO.getOrderDetails();
        List<ProductInfo> productInfos = orderDetails.stream().map(e -> productInfoService.findOne(e.getProductId())).collect(Collectors.toList());
        //商品沽清需要回滚
        if (productInfos.size() == 0) {
            throw new ProductException(ResultEnums.PRODUCT_NOT_EXIST);
        }
        //1.计算商品总价
        orderDetails.forEach(e -> {
            ProductInfo one = productInfoService.findOne(e.getProductId());
            totalAccount = one.getProductPrice().add(new BigDecimal(e.getProductQuantity())).add(totalAccount);
            BeanUtils.copyProperties(one,e);
            e.setOrderId(id);
            //订单详情入库
            orderDetailRepository.save(e);
        });
        //订单入库
        orderDTO.setOrderStatus(OrderStatusEnums.NEW.getCode());
        orderDTO.setPayStatus(PayStatusEnums.WAIT.getCode());
        orderDTO.setOrderAmount(totalAccount);
        orderDTO.setOrderId(id);
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMasterRepository.save(orderMaster);
        //扣库存
        List<CartDTO> collect = orderDetails.stream().map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productInfoService.deCreaseStock(collect);
        return orderDTO;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        //1.判断订单状态
        if(orderDTO.getOrderStatus()!=0){
            log.error("[完结订单]订单状态错误, orderId={}",orderDTO.getOrderId());
            throw new ProductException(ResultEnums.ORDER_STATUS_ERROR);
        }
        //2.修改订单状态为已完结
        orderDTO.setOrderStatus(OrderStatusEnums.FINISHED.getCode());
        OrderMaster converter = OrderDto2OrderMasterConverter.converter(orderDTO);
        OrderMaster save = orderMasterRepository.save(converter);
        return orderDTO;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        //1.判断是否是新订单
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnums.NEW.getCode())) {
            log.error("[支付订单]订单状态不正确,orderID={}",orderDTO.getOrderId());
            throw new ProductException(ResultEnums.ORDER_STATUS_ERROR);
        }
        //2,判断是否已支付
        if(!orderDTO.getPayStatus().equals(PayStatusEnums.WAIT.getCode())){
            log.error("[支付订单]支付状态不正确,orderID={}",orderDTO.getOrderId());
            throw new ProductException(ResultEnums.ORDER_PAY_STATUS_ERROR);
        }
        //3.更改支付状态为已支付
        orderDTO.setPayStatus(PayStatusEnums.SUCCESS.getCode());
        OrderMaster converter = OrderDto2OrderMasterConverter.converter(orderDTO);
        orderMasterRepository.save(converter);
        log.info("[支付订单]支付成功,orderID={}",orderDTO.getOrderId());
        return orderDTO;
    }
}
