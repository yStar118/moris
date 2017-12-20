package com.util.redis;

import com.util.spring.SpringContextHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;

/**
 * redis工具
 * 对象操作采用该类即可
 *
 * @author gaoyf
 */
//@Repository
public class RedisUtil {
    @SuppressWarnings("unchecked")
    private static RedisTemplate<Serializable, Serializable> redisTemplate = (RedisTemplate<Serializable, Serializable>) SpringContextHolder.getBean("redisTemplate");

    public static void set(final String key, Object value) {
        final byte[] vbytes = SerializeUtil.serialize(value);
        try {
            redisTemplate.execute((RedisCallback<Object>) connection -> {
                byte[] keybytes = redisTemplate.getStringSerializer().serialize(key);
                connection.set(keybytes, vbytes);
                return null;
            });
        } catch (Exception e) {
            System.out.println("------------");
            System.out.println(e.toString());
        }
    }

    public static void set(final String key, Object value, final long l) {
        final byte[] vbytes = SerializeUtil.serialize(value);
        redisTemplate.execute((RedisCallback<Object>) connection -> {
            connection.setEx(redisTemplate.getStringSerializer().serialize(key), l, vbytes);
            return null;
        });
    }

    public static <T> T get(final String key) {
        return redisTemplate.execute((RedisCallback<T>) connection -> {
            byte[] keybytes = redisTemplate.getStringSerializer().serialize(key);
            if (connection.exists(keybytes)) {
                byte[] valuebytes = connection.get(keybytes);
                @SuppressWarnings("unchecked")
                T value = (T) SerializeUtil.unserialize(valuebytes);
                return value;
            }
            return null;
        });
    }

    public static void del(final String key) {
        final byte[] keyBytes = redisTemplate.getStringSerializer().serialize(key);
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.del(keyBytes);
                return null;
            }
        });
    }

    ///清空所有
    public static void flushDb() {
        redisTemplate.execute((RedisCallback<Object>) connection -> {
            connection.flushDb();
            return null;
        });
    }
}
