package com.chuanfangn.sell.enums;

import lombok.Getter;

@Getter
public enum ProductStatusEnums {
    UP(0, "上架"),
    DOWN(1, " 下架");

    private Integer code;
    private String message;

    ProductStatusEnums(Integer integer, String message) {
        this.code = integer;
        this.message = message;
    }
}
