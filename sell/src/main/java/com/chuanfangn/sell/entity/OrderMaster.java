package com.chuanfangn.sell.entity;

import com.chuanfangn.sell.enums.OrderStatusEnums;
import com.chuanfangn.sell.enums.PayStausEnums;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@DynamicUpdate
@Entity
public class OrderMaster {
  /**订单id*/
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
  private BigDecimal orderAmount;
  /**订单状态,默认为新下单*/
  private Integer orderStatus= OrderStatusEnums.NEW.getCode();
  /**支付状态,默认为0未支付*/
  private Integer payStatus= PayStausEnums.WAIT.getCode();
  /**创建时间*/
  private java.sql.Timestamp createTime;
  /**更新时间*/
  private java.sql.Timestamp updateTime;
}
