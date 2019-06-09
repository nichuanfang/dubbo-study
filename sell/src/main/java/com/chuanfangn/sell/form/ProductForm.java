package com.chuanfangn.sell.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @description: 表单验证实体
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-06-08 23:16
 * @version:
 **/
@Data
public class ProductForm {
    private String productId;
    @NotBlank
    private String name;
    @NotNull
    private Double price;
    @NotNull
    private Integer stock;
    @NotBlank
    private String description;
    @NotNull
    private Integer category;
}
