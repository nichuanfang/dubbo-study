package com.chuanfangn.sell.serviceImpl;

import com.chuanfangn.sell.entity.ProductInfo;
import com.chuanfangn.sell.repository.ProductInfoRepository;
import com.chuanfangn.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-04-08 15:07
 * @version:
 **/
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    ProductInfoRepository productInfoRepository;

    @Override
    public ProductInfo findOne(String id) {
        ProductInfo byProductId = productInfoRepository.findByProductId(id);
        return byProductId;
    }

    @Override
    public List<ProductInfo> findByStatus(Integer integer) {
        List<ProductInfo> allByProductStatus = productInfoRepository.findAllByProductStatus(integer);
        return allByProductStatus;
    }

    @Override
    public List<ProductInfo> findAll() {
        List<ProductInfo> all = productInfoRepository.findAll();
        return all;
    }

    @Override
    public Page<ProductInfo> findAllViaPage(Pageable pageable) {
        Page<ProductInfo> all = productInfoRepository.findAll(pageable);
        return all;
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        ProductInfo save = productInfoRepository.save(productInfo);
        return save;
    }

    @Override
    public void delete(String id) {
        productInfoRepository.deleteById(id);
    }
}
