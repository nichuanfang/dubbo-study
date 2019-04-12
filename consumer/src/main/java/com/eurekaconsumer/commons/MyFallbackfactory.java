package com.eurekaconsumer.commons;

import com.eurekaconsumer.service.TestService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @description: 降级类工厂
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-04-10 05:24
 * @version:
 **/
@Component
public class MyFallbackfactory implements FallbackFactory<TestService> {
    @Override
    public TestService create(Throwable throwable) {
        return new TestService() {
            @Override
            public String test() {
                return "服务异常";
            }
        };
    }
}
