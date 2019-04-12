package com.chuanfangn.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @description: 商品详情vo
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-04-12 04:46
 * @version: 1.0
 **/
@Data
public class ProductInfoVo {
    /**商品id*/
    @JsonProperty("id")
    private String productId;
    /**商品名称*/
    @JsonProperty("name")
    private String productName;
    /**商品价格,涉及金额采用BigDecimal*/
    @JsonProperty("price")
    private BigDecimal productPrice;
    /** 商品描述*/
    @JsonProperty("description")
    private String productDescription;
    /**图标url*/
    @JsonProperty("icon")
    private String productIcon;
}
