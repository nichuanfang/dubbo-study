package com.chuanfangn.sell.service.serviceimpl;

import com.chuanfangn.sell.controller.websocket.Websocket;
import com.chuanfangn.sell.converter.OrderDto2OrderMasterConverter;
import com.chuanfangn.sell.converter.OrderMaster2OrderDtoConverter;
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
import com.chuanfangn.sell.service.PayService;
import com.chuanfangn.sell.service.ProductInfoService;
import com.chuanfangn.sell.utils.IdGenerateUtil;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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

    @Autowired
    private Websocket websocket;
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

    @Autowired
    PayService payService;
    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findById(orderId).orElse(null);
        if(orderMaster==null){
            throw new ProductException(ResultEnums.PRODUCT_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        List<OrderDetail> allByOrderId = orderDetailRepository.findAllByOrderId(orderId);
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderAmount(new BigDecimal(orderMaster.getOrderAmount()));
        orderDTO.setOrderDetails(allByOrderId);
        return orderDTO;
    }

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
            totalAccount = (one.getProductPrice().multiply(new BigDecimal(e.getProductQuantity()))).add(totalAccount);
            BeanUtils.copyProperties(one, e);
            e.setDetailId(IdGenerateUtil.getId());
            e.setOrderId(id);
            e.setProductPrice(one.getProductPrice().doubleValue());
            //订单详情入库
            orderDetailRepository.save(e);
        });
        //订单入库
        orderDTO.setOrderStatus(OrderStatusEnums.NEW.getCode());
        orderDTO.setPayStatus(PayStatusEnums.WAIT.getCode());
        orderDTO.setOrderId(id);
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(totalAccount.doubleValue());
        orderMasterRepository.save(orderMaster);
        //扣库存
        List<CartDTO> collect = orderDetails.stream().map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productInfoService.deCreaseStock(collect);
        websocket.sendMessage(orderDTO.getOrderId());
        return orderDTO;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        //1.判断订单状态
        if (orderDTO.getOrderStatus() != 0) {
            log.error("[完结订单]订单状态错误, orderId={}", orderDTO.getOrderId());
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
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnums.NEW.getCode())) {
            log.error("[支付订单]订单状态不正确,orderID={}", orderDTO.getOrderId());
            throw new ProductException(ResultEnums.ORDER_STATUS_ERROR);
        }
        //2,判断是否已支付
        if (!orderDTO.getPayStatus().equals(PayStatusEnums.WAIT.getCode())) {
            log.error("[支付订单]支付状态不正确,orderID={}", orderDTO.getOrderId());
            throw new ProductException(ResultEnums.ORDER_PAY_STATUS_ERROR);
        }
        //3.更改支付状态为已支付
        orderDTO.setPayStatus(PayStatusEnums.SUCCESS.getCode());
        OrderMaster converter = OrderDto2OrderMasterConverter.converter(orderDTO);
        orderMasterRepository.save(converter);
        log.info("[支付订单]支付成功,orderID={}", orderDTO.getOrderId());
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> getOrderDtoList(String buyerOpenid, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<OrderMaster> allByBuyerOpenid = orderMasterRepository.findAllByBuyerOpenid(buyerOpenid, pageable);
        Page<OrderDTO> orderDTOS = new PageImpl<OrderDTO>(allByBuyerOpenid.getContent().stream().map(orderMaster -> {
            OrderDTO orderDTO = new OrderDTO();
            BeanUtils.copyProperties(orderMaster,orderDTO);
            return orderDTO;
        }).collect(Collectors.toList()),pageable,allByBuyerOpenid.getTotalElements());
        return orderDTOS;
    }



    /**
     * 方法功能: 取消订单
     * @param orderId
     * @author f18326186224@gmail.com
     * @creatDate  2019/5/18 22:55
     * @return com.lly835.bestpay.model.RefundResponse
     */
    @Override
    public RefundResponse cancel(String orderId) {
        OrderDTO one = findOne(orderId);
        //判断订单是否存在
        if(one==null) {
            throw new ProductException(ResultEnums.ORDER_NOT_EXIST);
            //判断订单是否已支付
        }else if (!one.getPayStatus().equals(PayStatusEnums.SUCCESS.getCode())){
            throw new ProductException(ResultEnums.ORDER_PAY_STATUS_ERROR);
        }
        //退还库存
        List<OrderDetail> orderDetails = one.getOrderDetails();
        if(CollectionUtils.isEmpty(orderDetails)) {
           throw new ProductException(ResultEnums.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> collect = orderDetails.stream().map(orderDetail -> new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity())).collect(Collectors.toList());
        productInfoService.increaseStock(collect);
        //退款
        RefundResponse refund = payService.refund(orderId);
        if(refund!=null) {
            log.info("退款成功,Refundresponse={}", JsonUtil .toJson(refund));
        }
        //修改支付状态
        one.setPayStatus(PayStatusEnums.Refunding.getCode());
        OrderMaster save = save(one);
        log.info(JsonUtil.toJson(save));
        return refund;
    }

    @Override
    public OrderMaster save(OrderDTO orderDTO) {
        OrderMaster converter = OrderDto2OrderMasterConverter.converter(orderDTO);
        OrderMaster save = orderMasterRepository.save(converter);
        return save;
    }

    @Override
    public Page<OrderDTO> findAll(Integer page, Integer size) {
        PageRequest of = PageRequest.of(page, size);
        Page<OrderMaster> all = orderMasterRepository.findAll(of);
        // 将ordermaster转换为orderdto
        PageImpl<OrderDTO> orderDTOPage = new PageImpl<>(OrderMaster2OrderDtoConverter.getOrderDtoList(all.getContent()), all.getPageable(), all.getTotalElements());
        return orderDTOPage;
    }


}
