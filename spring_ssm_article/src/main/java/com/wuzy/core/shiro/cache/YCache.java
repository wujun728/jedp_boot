package com.wuzy.core.shiro.cache;

import org.apache.log4j.Logger;
import org.apache.shiro.cache.CacheException;

import com.wuzy.core.shiro.RedisManager;
import com.wuzy.core.utils.DataUtil;

import java.io.IOException;
import java.util.*;

/**
 * 缓存实现
 * @author Wujun
 * 2017年1月17日 下午10:20:21
 */
public class YCache<K, V> implements org.apache.shiro.cache.Cache<K, V> {

    private static final Logger logger = Logger.getLogger(YCache.class);

    private RedisManager redisManager;

    private String prefixKey = "WTCache_";

    private int dbIndex = 2;

    public V get(K key) throws CacheException {
        Object o = null;
        try {
            o = redisManager.get(key, dbIndex);
        } catch (IOException e) {
            logger.error("Error:", e);
        } catch (ClassNotFoundException e) {
            logger.error("Error:", e);
        }
        return (V) o;
    }

    public V put(K key, V value) throws CacheException {

        try {
            redisManager.set(prefixKey + key, value, dbIndex);
        } catch (IOException e) {
            logger.error("Error:", e);
        }
        return value;
    }

    public V remove(K key) throws CacheException {
        Object obj = null;
        try {
            obj = redisManager.get(key, dbIndex);
            redisManager.del(key, dbIndex);
        } catch (IOException e) {
            logger.error("Error:", e);
        } catch (ClassNotFoundException e) {
            logger.error("Error:", e);
        }
        return (V) obj;
    }

    public void clear() throws CacheException {
        redisManager.flushDb(dbIndex);
    }


    public int size() {
        Long size = redisManager.size(dbIndex);
        return size.intValue();
    }

    public Set<K> keys() {
        Set<K> set = new HashSet<K>();
        try {
            Set<byte[]> fuzzyKeys = redisManager.getFuzzyKeys(prefixKey + "*", dbIndex);
            if (fuzzyKeys == null) {
                return set;
            }
            for (byte[] b : fuzzyKeys) {
                Object o = DataUtil.getObjectFromByteArray(b);
                set.add((K) o);
            }
        } catch (IOException e) {
            logger.error("Error:", e);
        } catch (ClassNotFoundException e) {
            logger.error("Error:", e);
        }
        return set;
    }

    public Collection<V> values() {
        List<V> list = new ArrayList<V>();
        try {
            Set<byte[]> fuzzyKeys = redisManager.getFuzzyKeys(prefixKey + "*", dbIndex);
            if (fuzzyKeys == null || fuzzyKeys.isEmpty()) {
                return list;
            }
            List<Object> objectList = redisManager.mget(dbIndex, fuzzyKeys.toArray(new byte[][]{}));
            for (Object obj : objectList) {
                list.add((V) obj);
            }
        } catch (IOException e) {
            logger.error("Error:", e);
        } catch (ClassNotFoundException e) {
            logger.error("Error:", e);
        }

        return list;
    }

    public RedisManager getRedisManager() {
        return redisManager;
    }

    public void setRedisManager(RedisManager redisManager) {
        this.redisManager = redisManager;
    }

    public String getPrefixKey() {
        return prefixKey;
    }

    public void setPrefixKey(String prefixKey) {
        this.prefixKey = prefixKey;
    }

    public int getDbIndex() {
        return dbIndex;
    }

    public void setDbIndex(int dbIndex) {
        this.dbIndex = dbIndex;
    }
}
