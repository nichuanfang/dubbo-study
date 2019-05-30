package com.chuanfangn.sell.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jdk.nashorn.internal.ir.ReturnNode;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-05-06 11:45
 * @version:
 **/
public class JsonUtil {
    public static String format(Object o){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        String s = gson.toJson(o);
        return s;
    }
}
