package com.chuanfangn.sell.service;

import com.chuanfangn.sell.entity.ProductCategory;
import com.github.pagehelper.PageInfo;

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
     *
     * @param id
     * @return com.chuanfangn.sell.entity.ProductCategory
     * @author f18326186224@gmail.com
     * @creatDate 2019/4/8 14:55
     */
    ProductCategory findByCategoryId(Integer id);

    /**
     * 方法功能: 查询所有商品分类实体
     *
     * @param
     * @return java.util.List<com.chuanfangn.sell.entity.ProductCategory>
     * @author f18326186224@gmail.com
     * @creatDate 2019/4/8 14:56
     */
    List<ProductCategory> findAll();

    /**
     * 方法功能: 根据商品分类查询分类实体
     *
     * @param categoryType
     * @return com.chuanfangn.sell.entity.ProductCategory
     * @author f18326186224@gmail.com
     * @creatDate 2019/6/5 23:27
     */
    ProductCategory findByCategoryType(Integer categoryType);

    /**
     * 方法功能: 根据商品类别集合查询商品分类实体
     *
     * @param list
     * @return java.util.List<com.chuanfangn.sell.entity.ProductCategory>
     * @author f18326186224@gmail.com
     * @creatDate 2019/4/8 14:56
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> list);

    /**
     * 方法功能: 新增,更新商品分类信息
     *
     * @param productCategory
     * @return com.chuanfangn.sell.entity.ProductCategory
     * @author f18326186224@gmail.com
     * @creatDate 2019/4/8 15:00
     */
    ProductCategory save(ProductCategory productCategory);

    /**
     * 方法功能: 分页查询所有分类
     *
     * @param page
     * @param size
     * @return com.github.pagehelper.PageInfo<com.chuanfangn.sell.entity.ProductCategory>
     * @author f18326186224@gmail.com
     * @creatDate 2019/6/9 12:30
     */
    PageInfo<ProductCategory> findAll(Integer page, Integer size);
}
