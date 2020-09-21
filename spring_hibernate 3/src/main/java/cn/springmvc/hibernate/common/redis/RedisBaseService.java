package cn.springmvc.hibernate.common.redis;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author Vincent.wang
 *
 */
public abstract class RedisBaseService<K, V> {

    // @Resource(name = "redisTemplate")
    protected RedisTemplate<K, V> redisTemplate;

}