package com.chuanfangn.sell.Configuration;

import com.chuanfangn.sell.propertyclasses.PayProperties;
import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-05-06 11:28
 * @version:
 **/
@Configuration
public class WxPayConfiguration {
    @Autowired
    PayProperties payProperties;

    @Bean
    public BestPayService getBestPayService() {
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(getWxPayH5Config());
        return bestPayService;
    }

    @Bean
    public WxPayH5Config getWxPayH5Config() {
        WxPayH5Config wxPayH5Config = new WxPayH5Config();
        wxPayH5Config.setAppId(payProperties.getMpPayAppId());
        wxPayH5Config.setMchId(payProperties.getMchId());
        wxPayH5Config.setMchKey(payProperties.getMchKey());
        wxPayH5Config.setKeyPath(payProperties.getKeyPath());
        wxPayH5Config.setNotifyUrl("http://jayzhou.natapp1.cc/sell/pay/notify");
        return wxPayH5Config;
    }
}
