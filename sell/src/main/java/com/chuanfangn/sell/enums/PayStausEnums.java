package com.chuanfangn.sell.enums;

import lombok.Getter;

@Getter
public enum PayStausEnums {
    SUCCESS(0, "支付完成"),
    FAILED(1, "支付失败");
    private Integer code;
    private String msg;

    PayStausEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
