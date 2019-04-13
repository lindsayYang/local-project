package com.lindsay.test.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: Lindsay
 * @Date: 2018/11/30 18:03
 * @Description:
 */
@Component
public class RedisUtils {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    public Set keys(String pattern) {
        return stringRedisTemplate.keys("*" + pattern + "*");
    }

    public void del(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 存储String类型数据
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 存储String类型数据
     *
     * @param key
     * @param value
     * @param timeout 过期时间，单位：秒
     */
    public void set(String key, String value, long timeout) {
        stringRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 得到String类型数据
     *
     * @param key
     * @return
     */
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 存储object对象
     *
     * @param key
     * @param value
     */
    public void setObject(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 存储object对象
     *
     * @param key
     * @param value
     * @param timeout 过期时间，单位：秒
     */
    public void setObject(String key, Object value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 得到Object对象
     *
     * @param key
     * @return
     */
    public Object getObject(String key) {
        return redisTemplate.boundValueOps(key).get();
    }

}
