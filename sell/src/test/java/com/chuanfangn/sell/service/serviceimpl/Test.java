package com.chuanfangn.sell.service.serviceimpl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-05-11 22:50
 * @version:
 **/
public class Test {
    @org.junit.Test
    public void test(){
        String url = "http://sell.com";
        try {
            System.out.println(URLEncoder.encode(url,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
