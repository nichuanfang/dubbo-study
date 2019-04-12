package com.chuanfangn.sell.entity;

import lombok.Data;

@Data
public class OrderMaster {
  /**订单id*/  
  private String orderId;
  /**买家姓名*/
  private String buyerName;
  /**买家号码*/
  private String buyerPhone;
  /**买家地址*/
  private String buyerAddress;
  /**买家openid*/
  private String buyerOpenid;
  /**`订单数量*/
  private double orderAmount;
  /**订单状态*/
  private Integer orderStatus;
  /**支付状态*/
  private Integer payStatus;
  /**创建时间*/
  private java.sql.Timestamp createTime;
  /**更新时间*/
  private java.sql.Timestamp updateTime;
}
