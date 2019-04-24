package com.chuanfangn.sell.exception;

import com.chuanfangn.sell.enums.ResultEnums;

/**
 * @description:
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-04-15 01:29
 * @version:
 **/
public class ProductException extends RuntimeException{
    public ProductException(ResultEnums resultEnums){
        super(resultEnums.getMessage());
    }
    public ProductException(Integer code,String msg){
        super(msg);
    }
}
