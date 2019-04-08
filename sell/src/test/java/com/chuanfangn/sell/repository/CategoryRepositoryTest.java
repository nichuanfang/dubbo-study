package com.chuanfangn.sell.repository;

import com.chuanfangn.sell.entity.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryRepositoryTest {
    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void test(){
        ProductCategory byCategoryId = categoryRepository.findByCategoryId(1);
        System.out.println(byCategoryId);
    }

}
