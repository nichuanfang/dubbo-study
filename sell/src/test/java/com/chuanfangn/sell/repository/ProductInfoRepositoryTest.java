package com.chuanfangn.sell.repository;

import com.chuanfangn.sell.entity.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInfoRepositoryTest {
    @Autowired
    ProductInfoRepository productInfoRepository;

    @Test
    public void findByProductId() {
        ProductInfo byProductId = productInfoRepository.findByProductId("1");
        Assert.assertNotNull(byProductId);
    }

    @Test
    public void findAllByProductStatus() {
        List<ProductInfo> allByProductStatus = productInfoRepository.findAllByProductStatus(0);
        Assert.assertNotEquals(0,allByProductStatus.size());
    }

    @Test
    public void findAll(){
        List<ProductInfo> all = productInfoRepository.findAll();
        Assert.assertNotEquals(0,all.size());
    }
}
