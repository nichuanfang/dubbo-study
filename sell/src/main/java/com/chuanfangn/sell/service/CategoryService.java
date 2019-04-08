package com.chuanfangn.sell.service;

import com.chuanfangn.sell.entity.ProductCategory;

import java.util.List;

/**
 * @description: 商品分类业务层接口
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-04-08 02:36
 * @version:
 **/
public interface CategoryService {
    /**
     * 方法功能: 根据商品分类id查询商品分类实体
     * @param id
     * @author f18326186224@gmail.com
     * @creatDate  2019/4/8 14:55
     * @return com.chuanfangn.sell.entity.ProductCategory
     */
    ProductCategory findByCategoryId(Integer id);
    /**
     * 方法功能: 查询所有商品分类实体
     * @param
     * @author f18326186224@gmail.com
     * @creatDate  2019/4/8 14:56
     * @return java.util.List<com.chuanfangn.sell.entity.ProductCategory>
     */
    List<ProductCategory> findAll();
    /**
     * 方法功能: 根据商品类别集合查询商品分类实体
     * @param list
     * @author f18326186224@gmail.com
     * @creatDate  2019/4/8 14:56
     * @return java.util.List<com.chuanfangn.sell.entity.ProductCategory>
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> list);
    /**
     * 方法功能: 新增,更新商品分类信息
     * @param productCategory
     * @author f18326186224@gmail.com
     * @creatDate  2019/4/8 15:00
     * @return com.chuanfangn.sell.entity.ProductCategory
     */
    ProductCategory save(ProductCategory productCategory);
}
