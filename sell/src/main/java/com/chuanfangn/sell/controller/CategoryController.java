package com.chuanfangn.sell.controller;

import com.chuanfangn.sell.entity.ProductCategory;
import com.chuanfangn.sell.form.CategoryForm;
import com.chuanfangn.sell.service.CategoryService;
import com.chuanfangn.sell.utils.IdGenerateUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-06-09 12:34
 * @version:
 **/
@Controller
@RequestMapping("/seller/productcategory")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping("/list")
    public String findAll(@RequestParam(value = "page",defaultValue = "1")Integer page, @RequestParam(value = "size",defaultValue = "5")Integer size, Model model) {
        PageInfo<ProductCategory> categoryPageInfo = categoryService.findAll(page, size);
        model.addAttribute("pageInfo",categoryPageInfo);
        return "category/category";
    }

    @RequestMapping("/update")
    public String update(@RequestParam(value = "categoryId",required = false)Integer categoryId,Model model) {
        if(categoryId==null) {
            return "category/update";
        }
        ProductCategory byCategoryType = categoryService.findByCategoryId(categoryId);
        model.addAttribute("category",byCategoryType);
        return "category/update";
    }

    @RequestMapping("/submit")
    @ResponseBody
    public String submit(@Valid CategoryForm categoryForm, BindingResult bindingResult) {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName(categoryForm.getCategoryName());
        productCategory.setCategoryType(categoryForm.getCategoryType());
        if(bindingResult.hasErrors()) {
            throw new RuntimeException(bindingResult.getFieldError().getDefaultMessage());
        }else if(categoryForm.getCategoryId()==null) {
            //新增
            try {
                categoryService.save(productCategory);
            } catch (Exception e) {
                e.printStackTrace();
                 return "新增失败";
            }
            return "新增成功";
        }else{
            //修改
            ProductCategory byCategoryId = categoryService.findByCategoryId(categoryForm.getCategoryId());
            productCategory.setCategoryId(categoryForm.getCategoryId());
            productCategory.setUpdateTime(byCategoryId.getUpdateTime());
            productCategory.setCreateTime(byCategoryId.getCreateTime());
            try {
                categoryService.save(productCategory);
            } catch (Exception e) {
                e.printStackTrace();
                return "修改失败";
            }
            return "修改成功";
        }
    }
}
