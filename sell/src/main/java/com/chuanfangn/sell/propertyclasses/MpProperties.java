package com.chuanfangn.sell.propertyclasses;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-05-03 12:42
 * @version:
 **/
@Component
@ConfigurationProperties(prefix = "weixin")
@Data
public class MpProperties {
    private String mpAppId;
    private String mpAppSecret;
}
