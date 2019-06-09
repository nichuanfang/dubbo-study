package com.chuanfangn.sell.service.serviceimpl;

import com.chuanfangn.sell.entity.ProductCategory;
import com.chuanfangn.sell.mapper.ProductCategoryMapper;
import com.chuanfangn.sell.repository.CategoryRepository;
import com.chuanfangn.sell.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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
    CategoryRepository categoryRepository;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    ProductCategoryMapper productCategoryMapper;

    @Override
    public ProductCategory findByCategoryId(Integer id) {
        ProductCategory byCategoryId = categoryRepository.findByCategoryId(id);
        return byCategoryId;
    }

    @Override
    public List<ProductCategory> findAll() {
        List<ProductCategory> all = categoryRepository.findAll();
        return all;
    }

    @Override
    public ProductCategory findByCategoryType(Integer categoryType) {
        ProductCategory productCategory = categoryRepository.findByCategoryType(categoryType);
        return productCategory;
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> list) {
        List<ProductCategory> byCategoryTypeIn = categoryRepository.findByCategoryTypeIn(list);
        return byCategoryTypeIn;
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        ProductCategory save = categoryRepository.save(productCategory);
        return save;
    }

    @Override
    public PageInfo<ProductCategory> findAll(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<ProductCategory> productCategoryList = productCategoryMapper.findAll();
        PageInfo<ProductCategory> productCategoryPageInfo = new PageInfo<>(productCategoryList);
        return productCategoryPageInfo;
    }
}
