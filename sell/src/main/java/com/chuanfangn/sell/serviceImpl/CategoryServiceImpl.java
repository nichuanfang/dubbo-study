package com.chuanfangn.sell.serviceImpl;

import com.chuanfangn.sell.entity.ProductCategory;
import com.chuanfangn.sell.repository.MyRepository;
import com.chuanfangn.sell.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-04-08 02:55
 * @version:
 **/
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    MyRepository myRepository;

    @Override
    public ProductCategory findByCategoryId(Integer id) {
        ProductCategory byCategoryId = myRepository.findByCategoryId(id);
        return byCategoryId;
    }

    @Override
    public List<ProductCategory> findAll() {
        List<ProductCategory> all = myRepository.findAll();
        return all;
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> list) {
        List<ProductCategory> byCategoryTypeIn = myRepository.findByCategoryTypeIn(list);
        return byCategoryTypeIn;
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        ProductCategory save = myRepository.save(productCategory);
        return save;
    }
}
