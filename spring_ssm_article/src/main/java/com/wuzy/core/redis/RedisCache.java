package com.wuzy.core.redis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.cache.CacheException;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.util.CollectionUtils;
/**
 * Redis-shiro-Cache
 * @author wuzy
 * 2016年12月18日 下午9:42:42
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class RedisCache<K,V> implements Cache<K, V>{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private RedisManager cache;
	private String keyPrefix="wu_shiro_redis_session:";
	public RedisManager getCache() {
		return cache;
	}
	public void setCache(RedisManager cache) {
		this.cache = cache;
	}
	public String getKeyPrefix() {
		return keyPrefix;
	}
	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}
	/**
	 * 通过JedisManager 获取 RedisCache
	 * @param cache
	 */
	public RedisCache(RedisManager cache){
		if(cache==null){
			throw new IllegalArgumentException("Cache argument cannot be null.");
		}
		this.cache=cache;
	}
	/**
	 * Constructs a cache instance with the specified
	 * Redis manager and using a custom key prefix.
	 * @param cache The cache manager instance
	 * @param prefix The Redis key prefix
	 */
	public RedisCache(RedisManager cache, String prefix){
		this(cache);
		this.keyPrefix=prefix;
	}
	/**
	 * 获取redis中的数据
	 * @param key
	 * @return
	 */
	private byte[] getByteKey(K key){
		if(key instanceof String){
			String preKey=this.keyPrefix+key;
			return preKey.getBytes();
		}else{
			return SerializeUtils.serialize(key);
		}
	}
	public V get(K key) throws CacheException{
		logger.debug("通过key从redis中获取数据。key【"+key+"】");
		try {
			if(key == null){
				return null;
			}else{
				byte[] rawValue=cache.get(getByteKey(key));
				@SuppressWarnings("unchecked")
				V value =(V)SerializeUtils.deserialize(rawValue);
				return value;
			}
		} catch (Throwable t) {
			throw new CacheException(t);
		}
		
	}
	/**
	 * put
	 */
	public V put(K key,V value)throws CacheException{
		logger.debug("redis put方法》》key:"+key);
		
		try {
			cache.set(getByteKey(key), SerializeUtils.serialize(value));
			return value;
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}
	
	/**
	 * remove
	 */
	public V remove(K key)throws CacheException{
		logger.debug("redis remove方法》》key:"+key);
		try {
			V previous = get(key);
			cache.del(getByteKey(key));
			return previous;
		} catch (Throwable e) {
			throw new CacheException(e);
		}
	}
	/**
	 * clear
	 */
	public void clear() throws CacheException{
		logger.debug("redis clear方法》》");
		try {
            cache.flushDB();
        } catch (Throwable t) {
            throw new CacheException(t);
        }
		
	}
	
	/**
	 * size
	 */
	public int size() {
		try {
			Long longSize = new Long(cache.dbSize());
            return longSize.intValue();
        } catch (Throwable t) {
            throw new CacheException(t);
        }
	}
	@SuppressWarnings("unchecked")
	public Set<K> keys() {
		try {
            Set<byte[]> keys = cache.keys(this.keyPrefix + "*");
            if (CollectionUtils.isEmpty(keys)) {
            	return Collections.emptySet();
            }else{
            	Set<K> newKeys = new HashSet<K>();
            	for(byte[] key:keys){
            		
            		newKeys.add((K)key);
            	}
            	return newKeys;
            }
        } catch (Throwable t) {
            throw new CacheException(t);
        }
	}
	
	public Collection<V> values() {
		try {
            Set<byte[]> keys = cache.keys(this.keyPrefix + "*");
            if (!CollectionUtils.isEmpty(keys)) {
                List<V> values = new ArrayList<V>(keys.size());
                for (byte[] key : keys) {
                    @SuppressWarnings("unchecked")
					V value = get((K)key);
                    if (value != null) {
                        values.add(value);
                    }
                }
                return Collections.unmodifiableList(values);
            } else {
                return Collections.emptyList();
            }
        } catch (Throwable t) {
            throw new CacheException(t);
        }
	}
	

}
