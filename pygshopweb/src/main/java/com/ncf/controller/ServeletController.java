package com.ncf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pygmanager.service.Userservice;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: workspace
 * @create: 2019-03-29 00:38
 * @version:
 **/
@Controller
public class ServeletController {
    @Reference(check = false,retries = 2)
    Userservice userservice;
    @RequestMapping("/test")
    void test(){
        String test = userservice.test();
        System.out.println(test);
    }
}