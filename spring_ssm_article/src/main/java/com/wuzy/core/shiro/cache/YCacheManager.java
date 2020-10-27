package com.wuzy.core.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

/**
 * 缓存管理
 * @author wuzy
 * 2017年1月17日 下午10:20:49
 */
public class YCacheManager implements org.apache.shiro.cache.CacheManager{

    private Cache cache;

    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return cache;
    }

    public Cache getCache() {
        return cache;
    }

    public void setCache(Cache cache) {
        this.cache = cache;
    }
}
