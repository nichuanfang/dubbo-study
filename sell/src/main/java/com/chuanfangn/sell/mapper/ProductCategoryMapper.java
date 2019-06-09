package com.chuanfangn.sell.mapper;

import com.chuanfangn.sell.entity.ProductCategory;

import java.util.List;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-06-09 12:15
 * @version:
 **/
public interface ProductCategoryMapper {
    /**
     * 方法功能: 查询所有商品分类
     * @param
     * @author f18326186224@gmail.com
     * @creatDate  2019/6/9 12:26
     * @return java.util.List<com.chuanfangn.sell.entity.ProductCategory>
     */
    List<ProductCategory> findAll();
}
