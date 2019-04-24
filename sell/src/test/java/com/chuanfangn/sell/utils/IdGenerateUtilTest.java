package com.chuanfangn.sell.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class IdGenerateUtilTest {
    @Test
    public void getIdTest(){
        String id = IdGenerateUtil.getId();
        System.out.println(id);
    }

}
