package com.chuanfangn.sell.repository;

import com.chuanfangn.sell.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @description: 
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-04-07 13:50
 * @version:
 **/
public interface CategoryRepository extends JpaRepository<ProductCategory, Integer> {
    /**
     * 方法功能: 根据商品类别集合查询分类信息
     * @param list
     * @author f18326186224@gmail.com
     * @creatDate  2019/4/8 14:47
     * @return java.util.List<com.chuanfangn.sell.entity.ProductCategory>
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> list);
    
    /**
     * 方法功能: 根据分类id查询商品分类信息
     * @param id
     * @author f18326186224@gmail.com
     * @creatDate  2019/4/8 14:51
     * @return com.chuanfangn.sell.entity.ProductCategory
     */
    ProductCategory findByCategoryId(Integer id);

}
