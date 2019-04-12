package com.eurekaconsumer.service;

import com.eurekaconsumer.commons.MyFallbackfactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-04-10 04:56
 * @version:
 **/
public interface TestService {
    public String test();
}
