package com.test.testone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: workspace
 * @create: 2019-04-01 21:33
 * @version:
 **/
public class test {
    public static void main(String[] args) {
        String[] strings = {"f","t","a","f"};
         /*Arrays.sort(strings, new Comparator<String>() {
             @Override
             public int compare(String o1, String o2) {
                 //升序
                 return o2.compareTo(o1);
             }
         });*/
         Arrays.sort(strings,(String s1,String s2)->s1.compareTo(s2));
        List<String> strings1 = Arrays.asList(strings);
        strings1.forEach(s -> System.out.println(s));
    }
}
