package com.chuanfangn.sell.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: httpservletrequest工具类
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-05-28 22:32
 * @version:
 **/
public class RequestUtil {

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        return request;
    }

}
