package com.chuanfangn.sell.entity;

import com.chuanfangn.sell.utils.Date2LongJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**商品分类实体
 * @author Administrator*/
@Data
@Entity
@DynamicUpdate
@DynamicInsert
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
  @JsonSerialize(using = Date2LongJsonSerializer.class)
  private Date createTime;
  /**更新时间*/
  @JsonSerialize(using = Date2LongJsonSerializer.class)
  private Date updateTime;

}
