package com.chuanfangn.sell.dto;

import com.chuanfangn.sell.entity.OrderDetail;
import com.chuanfangn.sell.enums.OrderStatusEnums;
import com.chuanfangn.sell.enums.PayStatusEnums;
import com.chuanfangn.sell.utils.Date2LongJsonSerializer;
import com.chuanfangn.sell.utils.EnumUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @description:订单主表的包装pojo
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-04-14 22:41
 * @version:
 **/
@Data
public class OrderDTO {
    private String orderId;
    /**买家姓名*/
    private String buyerName;
    /**买家号码*/
    private String buyerPhone;
    /**买家地址*/
    private String buyerAddress;
    /**买家openid*/
    private String buyerOpenid;
    /**`订单总金额*/
    private BigDecimal orderAmount;
    /**订单状态,默认为新下单*/
    private Integer orderStatus;
    /**支付状态,默认为0未支付*/
    private Integer payStatus;
    /**订单详情集合*/
    private List<OrderDetail> orderDetails;

    /**创建时间*/
    @JsonSerialize(using = Date2LongJsonSerializer.class)
    private Date createTime;
    /**更新时间*/
    @JsonSerialize(using = Date2LongJsonSerializer.class)
    private Date updateTime;

    @JsonIgnore
    public OrderStatusEnums getOrderStatusEnums(){
        return EnumUtils.getByCode(orderStatus, OrderStatusEnums.class);

    }

    @JsonIgnore
    public PayStatusEnums getPayStatusEnums(){
     return EnumUtils.getByCode(payStatus, PayStatusEnums.class);
    }

}
