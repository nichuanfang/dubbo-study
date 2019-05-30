package com.chuanfangn.sell.Configuration;

import com.chuanfangn.sell.propertyclasses.MpProperties;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-05-03 12:45
 * @version:
 **/
@Configuration
public class MpConfiguration {

    @Autowired
    MpProperties mpProperties;

    @Bean
    public WxMpService getWxmpService() {
        WxMpServiceImpl wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(getWxMpInMemoryConfigStorage());
        return wxMpService;
    }

    @Bean
    public WxMpConfigStorage getWxMpInMemoryConfigStorage() {
        WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpInMemoryConfigStorage.setAppId(mpProperties.getMpAppId());
        wxMpInMemoryConfigStorage.setSecret(mpProperties.getMpAppSecret());
        return wxMpInMemoryConfigStorage;
    }

}
