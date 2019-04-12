package com.chuanfangn.sell.repository;

import com.chuanfangn.sell.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @description: 商品信息持久层接口
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-04-08 12:59
 * @version: 1.0
 **/
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {
    /**
     * 方法功能: 根据商品id查询详细商品信息实体
     * @param id
     * @author f18326186224@gmail.com
     * @creatDate  2019/4/8 14:52
     * @return com.chuanfangn.sell.entity.ProductInfo
     */
    ProductInfo findByProductId(String id);
    /**
     * 方法功能: 查询上下架的商品
     * @param status
     * @author f18326186224@gmail.com
     * @creatDate  2019/4/8 16:37
     * @return java.util.List<com.chuanfangn.sell.entity.ProductInfo>
     */
    List<ProductInfo> findAllByProductStatus(Integer status);
}
