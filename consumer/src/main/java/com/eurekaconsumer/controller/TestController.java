package com.eurekaconsumer.controller;

import com.eurekaconsumer.service.TestService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-04-10 04:43
 * @version:
 **/
@RestController
public class TestController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/s")
    public String s(){
        return restTemplate.getForEntity("http://provider/test",String.class).getBody();
    }

    /**
     * 方法功能: fallback
     * @param
     * @author f18326186224@gmail.com
     * @creatDate  2019/4/10 13:39
     * @return java.lang.String
     */
    public String fail(){
        return "failed";
    }
}
