package com.chuanfangn.sell.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 商品信息实体
 */
@Entity
@Data
@DynamicUpdate
public class ProductInfo {
  @Id
  private String productId;
  /**名字*/
  private String productName;
  /**单价*/
  private BigDecimal productPrice;
  /**库存*/
  private Integer productStock;
  /**商品描述*/
  private String productDescription;
  /**商品图标*/
  private String productIcon;
  /**商品状态*/
  private Integer productStatus;
  /**商品类型*/
  private Integer categoryType;
  /**创建时间*/
  private java.sql.Timestamp createTime;
  /**更新时间*/
  private java.sql.Timestamp updateTime;
}
