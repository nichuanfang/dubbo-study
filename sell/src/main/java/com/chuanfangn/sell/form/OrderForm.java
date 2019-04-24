package com.chuanfangn.sell.form;


import com.chuanfangn.sell.entity.OrderDetail;
import com.google.gson.Gson;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @description: 用于表单验证的实体
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-04-24 20:48
 * @version: 1.0
 **/
@Data
public class OrderForm {
    @NotBlank(message = "用户名不能为空")
    private String name;
    @NotBlank(message = "电话不能为空")
    private String phone;
    @NotBlank(message = "地址不能为空")
    private String address;
    @NotBlank(message = "openid不能为空")
    private String openid;
    @NotBlank(message = "购物车不能为空")
    private String items;
}
