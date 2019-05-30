package com.chuanfangn.sell.serviceImpl;

import com.chuanfangn.sell.controller.websocket.Websocket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-05-30 12:14
 * @version:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class WebsocketTest {
    @Autowired
    private Websocket websocket;

    @Test
    public void test(){
        websocket.sendMessage("你好呀");
    }
}
