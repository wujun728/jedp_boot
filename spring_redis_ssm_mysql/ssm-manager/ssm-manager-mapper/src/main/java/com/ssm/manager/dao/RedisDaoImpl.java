package com.ssm.manager.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.JedisPool;
@Repository
public class RedisDaoImpl implements RedisDao {
	@Autowired
	private JedisPool jedisPool;
	/* (non-Javadoc)
	 * @see com.ssm.manager.dao.RedisDao#get(java.lang.String)
	 */
	@Override
	public String get(String key) {
		return jedisPool.getResource().get(key);
	}
	
	/* (non-Javadoc)
	 * @see com.ssm.manager.dao.RedisDao#set(java.lang.String, java.lang.String)
	 */
	@Override
	public String set(String key,String value) {
		return jedisPool.getResource().set(key,value);
	}
	
	/* (non-Javadoc)
	 * @see com.ssm.manager.dao.RedisDao#hget(java.lang.String, java.lang.String)
	 */
	@Override
	public String hget(String hkey,String key) {
		return jedisPool.getResource().hget(hkey, key);
	}
	
	/* (non-Javadoc)
	 * @see com.ssm.manager.dao.RedisDao#hset(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Long hset(String hkey,String key,String value) {
		return jedisPool.getResource().hset(hkey, key, value);
	}

	@Override
	public Long del(String key) {
		
		return jedisPool.getResource().del(key);
	}
	
}
