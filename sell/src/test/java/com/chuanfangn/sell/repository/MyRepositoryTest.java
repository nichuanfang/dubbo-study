package com.chuanfangn.sell.repository;

import com.chuanfangn.sell.entity.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MyRepositoryTest {
    @Autowired
    MyRepository myRepository;

    @Test
    public void test(){
        ProductCategory byCategoryId = myRepository.findByCategoryId(1);
        System.out.println(byCategoryId);
    }

}
