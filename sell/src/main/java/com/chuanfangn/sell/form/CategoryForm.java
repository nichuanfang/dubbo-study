package com.chuanfangn.sell.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-06-09 14:50
 * @version:
 **/
@Data
public class CategoryForm {
    private Integer categoryId;
    @NotNull
    private String categoryName;
    @NotNull
    private Integer categoryType;
}
