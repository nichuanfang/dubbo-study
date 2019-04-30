package com.chuanfangn.sell.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author Administrator
 */
@Data
@Entity
@DynamicUpdate
@DynamicInsert
public class OrderDetail {
  @Id
  private String detailId;
  private String orderId;
  private String productId;
  private String productName;
  private BigDecimal productPrice;
  private Integer productQuantity;
  private String productIcon;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;
}
