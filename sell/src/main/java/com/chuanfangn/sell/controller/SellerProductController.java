package com.chuanfangn.sell.controller;

import com.chuanfangn.sell.entity.ProductCategory;
import com.chuanfangn.sell.entity.ProductInfo;
import com.chuanfangn.sell.enums.ResultEnums;
import com.chuanfangn.sell.exception.ProductException;
import com.chuanfangn.sell.form.ProductForm;
import com.chuanfangn.sell.service.CategoryService;
import com.chuanfangn.sell.service.ProductInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-06-05 21:29
 * @version:
 **/
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    ProductInfoService productInfoService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping("/list")
    public String findProductList(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "5") Integer size, Model model) {
        PageInfo<ProductInfo> bypagehelper;
        try {
            bypagehelper = productInfoService.findBypagehelper(page, size);
        } catch (Exception e) {
            throw new RuntimeException("没有商品清单");
        }
        model.addAttribute("pageinfo", bypagehelper);
        return "product/product";
    }

    @RequestMapping("/Up")
    public String up(@RequestParam("productId") String productId, Model model) {
        model.addAttribute("url", "/sell/seller/product/list");
        try {
            Boolean up = productInfoService.up(productId);
            if (up) {
                model.addAttribute("message", "[上架成功]");
                return "commons/success";
            } else {
                model.addAttribute("message", "[上架失败]");
                return "commons/fail";
            }
        } catch (Exception e) {
            model.addAttribute("message", "[上架失败]");
            return "commons/fail";
        }
    }

    @RequestMapping("/Down")
    public String down(@RequestParam("productId") String productId, Model model) {
        model.addAttribute("url", "/sell/seller/product/list");
        try {
            Boolean down = productInfoService.down(productId);
            if (down) {
                model.addAttribute("message", "[下架成功]");
                return "commons/success";
            } else {
                model.addAttribute("message", "[下架失败]");
                return "commons/fail";
            }
        } catch (Exception e) {
            model.addAttribute("message", "[下架失败]");
            return "commons/fail";
        }
    }


    @RequestMapping("/update")
    public String update(@RequestParam(value = "productId", required = false) String productId, Model model) {
        List<ProductCategory> productCategoryList = categoryService.findAll();
        model.addAttribute("categoryList", productCategoryList);
        if (productId == null) {
            //新增操作
            return "product/update";
        }
        //更新操作
        ProductInfo productInfo = productInfoService.findOne(productId);
        model.addAttribute("productInfo", productInfo);
        return "product/update";
    }

    @RequestMapping("/submit")
    public String submit(@Valid ProductForm productForm, BindingResult bindingResult, Model model) {
        model.addAttribute("url","/sell/seller/product/list");
        if (bindingResult.hasErrors()) {
            throw new ProductException(ResultEnums.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        try {
            productInfoService.update(productForm);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "更新失败");
            return "commons/fail";
        }
        model.addAttribute("message", "更新成功");
        return "commons/success";
    }
}
