package com.chuanfangn.sell.Configuration;

import com.chuanfangn.sell.dto.OrderDTO;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-05-27 20:28
 * @version:
 **/
@Configuration
public class TestConfiguration {
    @Bean
    public WxMpService wxMpService(){
        WxMpServiceImpl wxMpService = new WxMpServiceImpl();
        wxMpService.setRetrySleepMillis(1000);
        return wxMpService;
    }

    @Bean
    public WxMpService wxOpenService(){
        WxMpServiceImpl wxMpService = new WxMpServiceImpl();
        wxMpService.setRetrySleepMillis(2000);
        return wxMpService;
    }
}
