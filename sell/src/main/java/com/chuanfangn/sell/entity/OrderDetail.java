package com.chuanfangn.sell.entity;

import com.chuanfangn.sell.utils.Date2LongJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

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
    private Double productPrice;
    private Integer productQuantity;
    private String productIcon;
    @JsonSerialize(using = Date2LongJsonSerializer.class)
    private Date createTime;
    @JsonSerialize(using = Date2LongJsonSerializer.class)
    private Date updateTime;
}
