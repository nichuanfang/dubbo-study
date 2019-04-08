package com.chuanfangn.sell.serviceImpl;

import com.chuanfangn.sell.entity.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryServiceImplTest {

    @Autowired
    CategoryServiceImpl categoryService;

    @Test
    public void findByCategoryId() {
        ProductCategory byCategoryId = categoryService.findByCategoryId(1);
        System.out.println(byCategoryId);
    }

    @Test
    public void findAll() {
        List<ProductCategory> all = categoryService.findAll();
        all.forEach(productCategory -> System.out.println(productCategory));
    }

    @Test
    public void findByCategoryTypeIn() {
        List<Integer> list = Arrays.asList(3);
        List<ProductCategory> byCategoryTypeIn = categoryService.findByCategoryTypeIn(list);
        byCategoryTypeIn.forEach(productCategory -> System.out.println(productCategory));
    }

    @Test
    public void save() {
        ProductCategory byCategoryId = categoryService.findByCategoryId(1);
        byCategoryId.setCategoryName("h4s");
        ProductCategory save = categoryService.save(byCategoryId);
        System.out.println(save);
    }
}
