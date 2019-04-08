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
public interface MyRepository extends JpaRepository<ProductCategory,Integer> {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> list);
    ProductCategory findByCategoryId(Integer id);

}
