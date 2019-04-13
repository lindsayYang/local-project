package com.lindsay.test.utils;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.util.StringUtils;

public class JsonUtils<T> {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private JsonUtils() {
        OBJECT_MAPPER.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        OBJECT_MAPPER.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    public static ObjectMapper getInstance() {
        return OBJECT_MAPPER;
    }

    public static String obj2json(Object obj) throws Exception {
        return OBJECT_MAPPER.writeValueAsString(obj);
    }

    public static <T> T json2pojo(String jsonStr, Class<T> clazz) throws Exception {
        return OBJECT_MAPPER.readValue(jsonStr, clazz);
    }

    public static <T> List<T> json2List(String jsonStr, Class<T> clazz) throws Exception {
        JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(List.class, new Class[]{clazz});
        return (List)OBJECT_MAPPER.readValue(jsonStr, javaType);
    }

    public static <T> T json2obj(String jsonStr, TypeReference<T> typeReference) throws Exception {
        return OBJECT_MAPPER.readValue(jsonStr, typeReference);
    }

    public static <T> Map<String, Object> json2map(String jsonStr) throws Exception {
        return (Map)OBJECT_MAPPER.readValue(jsonStr, Map.class);
    }

    public static <T> Map<String, T> json2map(String jsonStr, Class<T> clazz) throws Exception {
        Map<String, Map<String, Object>> map = (Map)OBJECT_MAPPER.readValue(jsonStr, new TypeReference<Map<String, T>>() {
        });
        HashMap result = new HashMap(map.size());
        Iterator var4 = map.entrySet().iterator();

        while(var4.hasNext()) {
            Entry<String, Map<String, Object>> entry = (Entry)var4.next();
            result.put(entry.getKey(), map2pojo((Map)entry.getValue(), clazz));
        }

        return result;
    }

    /** @deprecated */
    @Deprecated
    public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz) throws Exception {
        List<Map<String, Object>> list = (List)OBJECT_MAPPER.readValue(jsonArrayStr, new TypeReference<List<T>>() {
        });
        List<T> result = new ArrayList();
        Iterator var4 = list.iterator();

        while(var4.hasNext()) {
            Map<String, Object> map = (Map)var4.next();
            result.add(map2pojo(map, clazz));
        }

        return result;
    }

    public static <T> List<T> json2Array(String jsonArrayStr, Class<T> clazz) throws Exception {
        List<T> list = (List)OBJECT_MAPPER.readValue(jsonArrayStr, new TypeReference<List<T>>() {
        });
        List<T> result = new ArrayList();

        list.forEach(var4 -> {
            result.add(var4);
        });

        return result;
    }

    public static <T> List<T> json2Array(String jsonArrayStr) throws Exception {
        return json2Array(jsonArrayStr, (Class)null);
    }

    public static <T> T map2pojo(Map map, Class<T> clazz) {
        return OBJECT_MAPPER.convertValue(map, clazz);
    }

    public static <T> T map2obj(Map<String, String> map, Class<T> clazz) {
        Map<String, String> mapString = new HashMap();
        Iterator var3 = map.entrySet().iterator();

        while(var3.hasNext()) {
            Entry<String, String> entry = (Entry)var3.next();
            if (entry.getValue() != null && !((String)entry.getValue()).isEmpty()) {
                mapString.put(entry.getKey(), entry.getValue());
            }
        }

        return OBJECT_MAPPER.convertValue(mapString, clazz);
    }

    public static <T> T bytes2pojo(byte[] bytes, Class<T> clazz) throws Exception {
        String bytesString = "";

        try {
            bytesString = new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException var4) {
            var4.printStackTrace();
        }

        return json2pojo(bytesString, clazz);
    }

    public static byte[] obj2bytes(Object object) throws Exception {
        String objString = obj2json(object);
        return StringUtils.isEmpty(objString) ? new byte[0] : objString.getBytes();
    }
}

