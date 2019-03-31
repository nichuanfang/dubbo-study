package com.test.managercontroller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pygmanager.service.Userservice;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: workspace
 * @create: 2019-03-28 15:14
 * @version:
 **/
@Controller
public class ManagerController {
//    check作用: 默认check=true 服务没启动,消费者无法调用远程服务,无法完成初始化,这里我们改为false
    @Reference(check = false)
    Userservice userservice;

    @RequestMapping("/test")
    public void test(){
        String test = userservice.test();
        System.out.println(test);
    }
}