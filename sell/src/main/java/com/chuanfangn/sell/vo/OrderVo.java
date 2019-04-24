package com.chuanfangn.sell.vo;

import lombok.Data;

import java.util.Map;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-04-24 21:33
 * @version:
 **/
@Data
public class OrderVo {
    /**状态码*/
    private String code;
    /**描述*/
    private String message;
    /**订单信息*/
    private Map<String,String> data;
}
