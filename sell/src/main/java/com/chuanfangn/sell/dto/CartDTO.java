package com.chuanfangn.sell.dto;

import lombok.Data;

/**
 * @description:封装了买了哪些商品的简单信息
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-04-14 22:38
 * @version:
 **/
@Data
public class CartDTO {
    /**商品id*/
    private String productId;
    /**商品数量*/
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
