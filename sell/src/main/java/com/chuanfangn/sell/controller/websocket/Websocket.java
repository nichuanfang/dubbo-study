package com.chuanfangn.sell.controller.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-05-30 11:57
 * @version:
 **/
@Component
@Slf4j
@ServerEndpoint("/websocket")
public class Websocket {
    /**websocket会话*/
    private Session session;
    /**websocket容器*/
    private static CopyOnWriteArraySet<Websocket> websocketSet = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onopen(Session session){
        log.info("开始通信");
        websocketSet.add(this);
        this.session = session;
    }

    @OnClose
    public void onclose(){
        websocketSet.remove(this);
        log.info("通信关闭");
    }

    @OnMessage
    public void onMessage(String message) {
        log.info(message);
    }

    public void sendMessage(String message){
        websocketSet.forEach(websocket -> {
            try {
                websocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
