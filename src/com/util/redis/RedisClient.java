package com.util.redis;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * 对象是字符串的使用该类，如果是对象，比如list map采用redisutil
 *
 * @param <K>
 * @param <V>
 * @author gaoyf
 */
@Component("redisClient")
public class RedisClient<K extends Serializable, V extends Serializable> {

    @Resource(name = "redisTemplate")
    protected RedisTemplate<K, V> redisTemplate;

    /**
     * 添加
     *
     * @param key
     * @param value
     * @return
     */
    public boolean add(final String key, final String value) {
        boolean resultBoolean = false;
        if (redisTemplate != null) {
            resultBoolean = redisTemplate.execute((RedisCallback<Boolean>) connection -> {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] keys = serializer.serialize(key);
                byte[] values = serializer.serialize(value);
                return connection.setNX(keys, values);
            });
        } else {
        }
        return resultBoolean;
    }

    /**
     *
     * @param key
     * @param value
     * @param l秒
     * @return
     */
    public boolean set(final String key, final String value, final long l) {
        boolean resultBoolean = false;
        if (redisTemplate != null) {
            resultBoolean = redisTemplate.execute((RedisCallback<Boolean>) connection -> {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] keys = serializer.serialize(key);
                byte[] values = serializer.serialize(value);
                connection.setEx(keys, l, values);
                return true;
            });
        } else {
        }
        return resultBoolean;
    }

    /**
     * 根据key获取对象
     */
    public String get(final String key) {
        String resultStr = null;
        if (redisTemplate != null) {
            resultStr = redisTemplate.execute((RedisCallback<String>) connection -> {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] keys = serializer.serialize(key);
                byte[] values = connection.get(keys);
                if (values == null) {
                    return null;
                }
                String value = serializer.deserialize(values);
                return value;
            });
        }
        return resultStr;
    }

    /**
     * 判断是否存在
     *
     * @param key
     * @return
     */
    public boolean contain(final String key) {
        String resultStr = null;
        if (redisTemplate != null) {
            resultStr = redisTemplate.execute((RedisCallback<String>) connection -> {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] keys = serializer.serialize(key);
                byte[] values = connection.get(keys);
                if (values == null) {
                    return null;
                }
                return serializer.deserialize(values);
            });
        }
        return resultStr != null;
    }

    /**
     * 删除key
     *
     * @param key
     */
    public void del(final String key) {
        if (redisTemplate != null) {
            redisTemplate.execute((RedisCallback<Boolean>) connection -> {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] keys = serializer.serialize(key);
                connection.del(keys);
                return true;
            });
        } else {
            System.out.println("redisTemplate == null");
        }
    }

    /**
     * 设置过期时间
     *
     * @param key
     * @param l
     * @return
     */
    public boolean expire(final String key, final long l) {
        boolean resultBoolean = false;
        if (redisTemplate != null) {
            resultBoolean = redisTemplate.execute((RedisCallback<Boolean>) connection -> {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] keys = serializer.serialize(key);
                connection.expire(keys, l);
                return true;
            });
        } else {
            System.out.println(redisTemplate == null);
        }
        return resultBoolean;
    }

    /**
     * 获取 RedisSerializer
     *
     */
    protected RedisSerializer<String> getRedisSerializer() {
        return redisTemplate.getStringSerializer();
    }

    public RedisTemplate<K, V> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<K, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
