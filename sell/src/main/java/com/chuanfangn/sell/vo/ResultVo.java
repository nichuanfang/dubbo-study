package com.chuanfangn.sell.vo;

import lombok.Data;

import java.util.List;

/**
 * @description: http请求返回的与视图交互的对象,实际开发中不暴露数据库实体,为了保护隐私,而是对返回的对象进行映射封装
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-04-12 04:42
 * @version: 1.0
 **/
@Data
public class ResultVo {
    /**状态码*/
    private Integer code;
    /**描述*/
    private String msg;
    private List<ProductVo> data;
}
