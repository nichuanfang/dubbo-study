package com.chuanfangn.sell.service;

import com.chuanfangn.sell.entity.ProductCategory;

import java.util.List;

/**
 * @description: 商品分类业务层
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-04-08 02:36
 * @version:
 **/
public interface CategoryService {
    /**
     * @Description:根据类别id查询分类
     * @Author: 1290274972@qq.com
     * @Param: [id]
     * @return: com.chuanfangn.sell.entity.ProductCategory
     * @Date: 2019/4/8
     */
    ProductCategory findByCategoryId(Integer id);
    /**
     * @Description: 查询所有分类信息
     * @Author: 1290274972@qq.com
     * @Param: []
     * @return: java.util.List<com.chuanfangn.sell.entity.ProductCategory>
     * @Date: 2019/4/8
     */
    List<ProductCategory> findAll();

    /**
     * @Description: 根据商品分类id集合查询所有的分类信息实体
     * @Author: 1290274972@qq.com
     * @Param: [list]
     * @return: java.util.List<com.chuanfangn.sell.entity.ProductCategory>
     * @Date: 2019/4/8
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> list);
    /**
     * @Description: 更新或新增
     * @Author: 1290274972@qq.com
     * @Param: [productCategory]
     * @return: com.chuanfangn.sell.entity.ProductCategory
     * @Date: 2019/4/8
     */
    ProductCategory save(ProductCategory productCategory);
}
