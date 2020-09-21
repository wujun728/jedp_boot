package com.wuzy.core.shiro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.wuzy.core.utils.DataUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * RedisManager redis的增删改查
 * @author wuzy
 * 2017年1月2日 下午12:39:48
 */
public class RedisManager {

	private JedisPool jedisPool;
	
	public void publish(String key,String val){
        Jedis jedis = jedisPool.getResource();
        try {
             jedis.publish(key,val);
        } finally {
            jedis.close();
        }
    }


    public String set(Object key, Object val, int dbIndex) throws IOException {
        Jedis jedis = jedisPool.getResource();
        jedis.select(dbIndex);
        try {
            return jedis.set(DataUtil.getBytesFromObject(key), DataUtil.getBytesFromObject(val));
        } finally {
            jedis.close();
        }
    }

    public Object get(Object key, int dbIndex) throws IOException, ClassNotFoundException {
        Jedis jedis = jedisPool.getResource();
        jedis.select(dbIndex);
        try {

            byte[] bytes = jedis.get(DataUtil.getBytesFromObject(key));
            return DataUtil.getObjectFromByteArray(bytes);
        } finally {
            jedis.close();
        }
    }

    public Long del(Object key, int dbIndex) throws IOException {
        Jedis jedis = jedisPool.getResource();
        jedis.select(dbIndex);
        try {
            return jedis.del(DataUtil.getBytesFromObject(key));
        } finally {
            jedis.close();
        }
    }

    public Set<byte[]> getFuzzyKeys(Object key, int dbIndex) throws IOException {
        Jedis jedis = jedisPool.getResource();
        jedis.select(dbIndex);
        try {
            return jedis.keys(DataUtil.getBytesFromObject(key));
        } finally {
            jedis.close();
        }
    }

    public void flushDb(int dbIndex) {
        Jedis jedis = jedisPool.getResource();
        jedis.select(dbIndex);
        try {
            jedis.flushDB();
        } finally {
            jedis.close();
        }
    }

    public Long size(int dbIndex) {
        Jedis jedis = jedisPool.getResource();
        jedis.select(dbIndex);
        try {
            return jedis.dbSize();
        } finally {
            jedis.close();
        }
    }

    public void flushAll() {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.flushAll();
        } finally {
            jedis.close();
        }
    }

    public List<Object> mget(int dbIndex, byte[]... keys) throws IOException, ClassNotFoundException {
        List<Object> list = new ArrayList<Object>();
        Jedis jedis = jedisPool.getResource();
        jedis.select(dbIndex);
        try {
            List<byte[]> bytes = jedis.mget(keys);
            if (bytes == null) {
                return list;
            }
            for (byte[] b : bytes) {
                Object o = DataUtil.getObjectFromByteArray(b);
                list.add(o);
            }
        } finally {
            jedis.close();
        }
        return list;
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }
}
