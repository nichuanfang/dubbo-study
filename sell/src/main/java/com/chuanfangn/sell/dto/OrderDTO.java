package com.chuanfangn.sell.dto;

import com.chuanfangn.sell.entity.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
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
    private Timestamp createTime;
    /**更新时间*/
    private Timestamp updateTime;

}
