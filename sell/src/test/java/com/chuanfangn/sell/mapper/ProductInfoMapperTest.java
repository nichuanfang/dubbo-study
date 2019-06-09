package com.chuanfangn.sell.mapper;

import com.chuanfangn.sell.entity.ProductInfo;
import com.chuanfangn.sell.utils.JsonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInfoMapperTest {
    @Autowired
    ProductInfoMapper productInfoMapper;

    @Test
    public void test() {
        PageHelper.startPage(0,2);
        List<ProductInfo> productInfoList = productInfoMapper.findAll();
        PageInfo<ProductInfo> productInfoPageInfo = new PageInfo<>(productInfoList);
        String format = JsonUtil.format(productInfoPageInfo);
        System.out.println(format);
    }
}
