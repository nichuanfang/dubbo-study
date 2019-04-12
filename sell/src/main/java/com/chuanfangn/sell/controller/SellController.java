package com.chuanfangn.sell.controller;

import com.chuanfangn.sell.entity.ProductCategory;
import com.chuanfangn.sell.entity.ProductInfo;
import com.chuanfangn.sell.enums.ProductStatusEnums;
import com.chuanfangn.sell.service.CategoryService;
import com.chuanfangn.sell.service.ProductInfoService;
import com.chuanfangn.sell.utils.VoUtil;
import com.chuanfangn.sell.vo.ProductInfoVo;
import com.chuanfangn.sell.vo.ProductVo;
import com.chuanfangn.sell.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: shop description~
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-04-08 22:15
 * @version: 0.01
 **/
@RestController
public class SellController {

    @Autowired
    ProductInfoService productInfoService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/list")
    public ResultVo test(){
        List<ProductInfo> productInfoList = productInfoService.findByStatus(ProductStatusEnums.UP.getCode());
        ArrayList<Integer> arrayList = new ArrayList();
        productInfoList.forEach(productInfo -> arrayList.add(productInfo.getCategoryType()));
        List<ProductCategory> byCategoryTypeIn = categoryService.findByCategoryTypeIn(arrayList);
        List<ProductVo> productVos = new ArrayList<>();
        for (ProductCategory productCategory : byCategoryTypeIn) {
            List<ProductInfoVo> productInfoVos = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if((productInfo.getCategoryType()).equals(productCategory.getCategoryType())){
                    ProductInfoVo ProductInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo, ProductInfoVo);
                    productInfoVos.add(ProductInfoVo);
                }
            }
            ProductVo productVo = new ProductVo();
            productVo.setCategoryType(productCategory.getCategoryType());
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setProductInfoVos(productInfoVos);
            productVos.add(productVo);
        }
        ResultVo success = VoUtil.success(productVos);
        return success;
    }

}
