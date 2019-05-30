package com.chuanfangn.sell.utils;

/**
 * @description: 判断金额是否相等
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-05-14 15:39
 * @version:
 **/
public class MathUtil {
    public static final Double DOUBLE_RANGE = 0.01;
    public static Boolean compare(Double aDouble,Double bDouble){
        Double range = Math.abs(aDouble-bDouble);
        if(range<DOUBLE_RANGE){
            return true;
        }
        return false;
    }
}
