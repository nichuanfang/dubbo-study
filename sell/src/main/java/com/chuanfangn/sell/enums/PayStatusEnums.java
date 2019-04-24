package com.chuanfangn.sell.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnums {
    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功");
    private Integer code;
    private String msg;

    PayStatusEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
