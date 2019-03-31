package com.pygmanager.service;


import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: workspace
 * @create: 2019-03-27 16:03
 * @version:
 **/
@Service
@Component
public class UserServiceImpl implements Userservice {
    @Override
    public String test() {
        return "s";
    }
}
