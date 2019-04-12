package com.chuanfangn.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @description: 分类+商品集合,整合的实体
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-04-12 04:44
 * @version: 1.0
 **/
@Data
public class ProductVo {
    /**分类名称*/
    @JsonProperty("name")
    private String categoryName;

    /**类目编号*/
    @JsonProperty("type")
    private Integer categoryType;

    /**商品集合*/
    @JsonProperty("foods")
    private List<ProductInfoVo> productInfoVos;
}
