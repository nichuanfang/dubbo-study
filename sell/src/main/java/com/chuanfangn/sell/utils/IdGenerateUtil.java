package com.chuanfangn.sell.utils;

import java.util.Random;

/**
 * @description: 生成商品id  格式: 6位随机数_毫秒数
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-04-14 23:09
 * @version: 1.0
 **/
public class IdGenerateUtil {
    public static String getId(){
        long l = System.currentTimeMillis();
        Random random = new Random();
//        随机六位数
        int i = random.nextInt(900000) + 100000;
        return String.valueOf(i)+"_"+String.valueOf(l);
    }
}
