package com.chuanfangn.sell.entity;

import com.chuanfangn.sell.enums.OrderStatusEnums;
import com.chuanfangn.sell.enums.PayStatusEnums;
import com.chuanfangn.sell.utils.Date2LongJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Administrator
 */
@Data
@DynamicUpdate
@Entity
@DynamicInsert
public class OrderMaster {
  /**订单id*/
  @Id
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
  private Double orderAmount;
  /**订单状态,默认为新下单*/
  private Integer orderStatus= OrderStatusEnums.NEW.getCode();
  /**支付状态,默认为0未支付*/
  private Integer payStatus= PayStatusEnums.WAIT.getCode();
  /**创建时间*/
  @JsonSerialize(using = Date2LongJsonSerializer.class)
  private Date createTime;
  /**更新时间*/
  @JsonSerialize(using = Date2LongJsonSerializer.class)
  private Date updateTime;
}
