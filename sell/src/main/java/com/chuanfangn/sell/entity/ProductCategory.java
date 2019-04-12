package com.chuanfangn.sell.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**商品分类实体
 * @author Administrator*/
@Data
@Entity
@DynamicUpdate
public class ProductCategory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  /**分类id*/
  private Integer categoryId;
  /**类别名称*/
  private String categoryName;
  /**类目编号*/
  private Integer categoryType;
  /**创建时间*/
  private java.sql.Timestamp createTime;
  /**更新时间*/
  private java.sql.Timestamp updateTime;

}
