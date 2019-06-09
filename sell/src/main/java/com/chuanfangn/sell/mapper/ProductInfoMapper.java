package com.chuanfangn.sell.mapper;

import com.chuanfangn.sell.entity.ProductCategory;
import com.chuanfangn.sell.entity.ProductInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-06-06 17:15
 * @version:
 **/
public interface ProductInfoMapper {
    /**
     * 方法功能: 查询所有商品信息
     * @param
     * @author f18326186224@gmail.com
     * @creatDate  2019/6/6 17:27
     * @return java.util.List<com.chuanfangn.sell.entity.ProductInfo>
     */
    List<ProductInfo> findAll() ;

    /**
     * 方法功能: 查询商品列表
     * @param productId
     * @author f18326186224@gmail.com
     * @creatDate  2019/6/6 19:45
     * @return com.chuanfangn.sell.entity.ProductInfo
     */
    ProductInfo findByProductId(@Param("productId") String productId);

    /**
     * 方法功能: 添加数据
     * @param productInfo
     * @author f18326186224@gmail.com
     * @creatDate  2019/6/6 19:58
     * @return com.chuanfangn.sell.entity.ProductInfo
     */
    int update(@Param("productInfo") ProductInfo productInfo);

    int add(@Param("productInfo")ProductInfo productInfo);

    int updateAll(@Param("productInfo")ProductInfo productInfo);
}
