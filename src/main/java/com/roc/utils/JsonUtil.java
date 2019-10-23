package com.roc.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * @author Roc
 * @version 1.0
 * @date 2019/10/23 21:53
 */
public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static JsonParser jp = new JsonParser();

    public static JsonObject parse(String json){
        return jp.parse(json).getAsJsonObject();
    }

    //对象转字符串
    public static <T> String obj2String(T obj){
        if (obj == null){
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //字符串转对象
    public static <T> T string2Obj(String str,Class<T> clazz){
        if (StringUtils.isEmpty(str) || clazz == null){
            return null;
        }
        try {
            return clazz.equals(String.class)? (T) str :objectMapper.readValue(str,clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
