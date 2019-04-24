package com.chuanfangn.sell.serviceImpl;

import com.chuanfangn.sell.entity.ProductInfo;
import com.chuanfangn.sell.enums.ProductStatusEnums;
import com.chuanfangn.sell.service.serviceimpl.ProductInfoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ProductInfoServiceImplTest {
    @Autowired
    ProductInfoServiceImpl productInfoService;

    @Test
    public void findOne() {
        ProductInfo one = productInfoService.findOne("1");
        Assert.assertNotNull(one);
    }

    @Test
    public void findByStatus() {
        //查询上架商品
        List<ProductInfo> byStatus = productInfoService.findByStatus(ProductStatusEnums.UP.getCode());
        Assert.assertNotEquals(0,byStatus.size());
    }

    @Test
    public void findAll() {
        List<ProductInfo> all = productInfoService.findAll();
        Assert.assertNotEquals(0,all.size());
        log.info("测试通过");
    }

    @Test
    public void findAllViaPage() {
        log.info("以后测试...");
    }

    @Test
    public void save() {
        ProductInfo one = productInfoService.findOne("1");
        one.setCategoryType(2);
        ProductInfo save = productInfoService.save(one);
        Assert.assertNotNull(save);
    }
    @Test
    public void delete(){
        productInfoService.delete("1");
    }
}
