package com.chuanfangn.sell.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnums {
    ONLINE(0, "已下单"),
    OFFLINE(1, "未完成");
    private Integer code;
    private String msg;

    OrderStatusEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
