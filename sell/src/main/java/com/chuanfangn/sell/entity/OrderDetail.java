package com.chuanfangn.sell.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@DynamicUpdate
public class OrderDetail {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String detailId;
  private String orderId;
  private String productId;
  private String productName;
  private double productPrice;
  private long productQuantity;
  private String productIcon;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;
}
