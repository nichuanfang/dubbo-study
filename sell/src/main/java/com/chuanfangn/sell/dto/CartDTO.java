package com.chuanfangn.sell.dto;

import lombok.Data;

/**
 * @description: 购物车DTO
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-04-14 22:06
 * @version: 1.0
 **/
@Data
public class CartDTO {
    /**商品名称*/
    private String productId;
    /**购买数量*/
    private Integer productQuantity;
}
