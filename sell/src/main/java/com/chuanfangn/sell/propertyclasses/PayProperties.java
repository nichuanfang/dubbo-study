package com.chuanfangn.sell.propertyclasses;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-05-06 11:30
 * @version:
 **/
@Component
@Data
@ConfigurationProperties(prefix = "weixin")
public class PayProperties {
    private String mpPayAppId;
    private String mchId;
    private String mchKey;
    private String keyPath;
}
