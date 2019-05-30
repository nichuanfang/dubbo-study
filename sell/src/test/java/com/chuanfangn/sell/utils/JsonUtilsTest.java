package com.chuanfangn.sell.utils;
import com.chuanfangn.sell.entity.OrderMaster;
import org.junit.Test;

import java.util.Timer;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-05-18 22:40
 * @version:
 **/
public class JsonUtilsTest {
    @Test
    public void test(){
        OrderMaster orderMaster = new OrderMaster();
        String s = JsonUtil.format(orderMaster);
        System.out.println(s);
    }
}
