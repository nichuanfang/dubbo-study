package com.chuanfangn.sell.entity;

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
 * 商品信息实体
 * @author Administrator
 */
@Entity
@Data
@DynamicUpdate
@DynamicInsert
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
  /**商品状态,0上架 1下架*/
  private Integer productStatus;
  /**类型编号*/
  private Integer categoryType;
  /**创建时间*/
  @JsonSerialize(using = Date2LongJsonSerializer.class)
  private Date createTime;
  /**更新时间*/
  @JsonSerialize(using = Date2LongJsonSerializer.class)
  private Date updateTime;
}
