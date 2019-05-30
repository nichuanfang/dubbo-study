package com.chuanfangn.sell.utils;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * @description: date 类型json序列化 为 long 类型
 * @author: 1290274972@qq.com
 * @program: sell
 * @create: 2019-04-30 21:05
 * @version:
 **/
public class Date2LongJsonSerializer extends JsonSerializer<Date> {
    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(date.getTime()/1000);
    }
}
