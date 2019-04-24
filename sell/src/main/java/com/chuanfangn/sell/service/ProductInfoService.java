package com.chuanfangn.sell.service;

import com.chuanfangn.sell.dto.CartDTO;
import com.chuanfangn.sell.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @description:商品信息业务接口
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-04-08 13:08
 * @version:
 **/
public interface ProductInfoService {
    /**
     * 方法功能: 根据商品id查询商品详情
     * @param id
     * @author f18326186224@gmail.com
     * @creatDate  2019/4/8 15:01
     * @return com.chuanfangn.sell.entity.ProductInfo
     */
    ProductInfo findOne(String id);
    /**
     * 方法功能: 查询上下架商品
     * @param integer
     * @author f18326186224@gmail.com
     * @creatDate  2019/4/8 16:52
     * @return java.util.List<com.chuanfangn.sell.entity.ProductInfo>
     */
    List<ProductInfo> findByStatus(Integer integer);

    /**
     * 方法功能: 查询所有商品
     * @param
     * @author f18326186224@gmail.com
     * @creatDate  2019/4/8 16:54
     * @return java.util.List<com.chuanfangn.sell.entity.ProductInfo>
     */
    List<ProductInfo> findAll();
    /**
     * 方法功能: 分页查询商品
     * @param pageable
     * @author f18326186224@gmail.com
     * @creatDate  2019/4/8 16:56
     * @return java.util.List<com.chuanfangn.sell.entity.ProductInfo>
     */
    Page<ProductInfo> findAllViaPage(Pageable pageable);
    /**
     * 方法功能: 新增,更新商品
     * @param productInfo
     * @author f18326186224@gmail.com
     * @creatDate  2019/4/8 16:57
     * @return com.chuanfangn.sell.entity.ProductInfo
     */
    ProductInfo save(ProductInfo productInfo);
    /**
     * 方法功能:  删除商品
     * @param id
     * @author f18326186224@gmail.com
     * @creatDate  2019/4/8 17:33
     * @return void
     */
    void delete(String id);

    /**
     * 方法功能: 减库存
     * @param collect
     * @author f18326186224@gmail.com
     * @creatDate  2019/4/15 1:29
     * @return void
     */
    void deCreaseStock(List<CartDTO> collect);

    //加库存,待完善
}
