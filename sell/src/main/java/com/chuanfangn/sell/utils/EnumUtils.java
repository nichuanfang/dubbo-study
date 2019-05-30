package com.chuanfangn.sell.utils;

import com.chuanfangn.sell.enums.CodeEnum;

/**
 * @description: 枚举工具
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-05-24 23:55
 * @version:
 **/
public class EnumUtils {
    public static <T extends CodeEnum<Integer>> T getByCode(Integer code,Class<T> tClass){
        for (T t : tClass.getEnumConstants()) {
            if(t.getCode().equals(code)){
                return t;
            }
        }
        return  null;
    }
 }
