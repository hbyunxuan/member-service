package com.kykj.tesla.member.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JacksonUtils {
    public JacksonUtils() {
    }

    public static final String toJson(ObjectMapper objectMapper, Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException var3) {
            throw new RuntimeException("jackson序列化toJson异常", var3);
        }
    }

    public static final <T> T toObject(ObjectMapper objectMapper, String jsonText, Class<T> tClass) {
        try {
            return objectMapper.readValue(jsonText, tClass);
        } catch (IOException var4) {
            throw new RuntimeException("jackson序列化toObject异常", var4);
        }
    }

}
