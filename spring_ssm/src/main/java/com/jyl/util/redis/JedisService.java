/**
 * Copyright (C) 2016 Juno Inc., All Rights Reserved.
 */
package com.jyl.util.redis;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.util.Assert;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 *
 * @author Long, E-mail:jyl0401@163.com
 * @date 2016年7月6日 下午8:47:56
 */
public class JedisService {
	
	private boolean redisSwitch = false;
	
	private String serverIp;
	
	private int serverPort;
	
	private JedisPool pool;
	
	@PostConstruct
	public void init() throws IOException{
		
		if(false == redisSwitch){
			return;
		}
		
		JedisPoolConfig config = new JedisPoolConfig();
		//控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
		//如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
		config.setMaxTotal(30);
		//控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
		config.setMaxIdle(5);
		//表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
		config.setMaxWaitMillis(5000);
		//在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
		config.setTestOnBorrow(true);
		pool = new JedisPool(config, serverIp, serverPort);
		Jedis test = this.borrow();
		String key = "test.foo";
		String value = "bar";
		test.set(key, value);
		String testValue = test.get(key);
		test.del(key);
		System.out.println("setnx:" + test.setnx("test.setnx", testValue));
		test.expire(key, 40);
		this.returnResource(test);
		Assert.isTrue(value.equals(testValue));
		System.out.println("启动 redis 连接池 成功" );
		
	}
	
	/**
	 * 还回到连接池
	 * @param redis
	 */
	public void returnResource(Jedis redis) {
	    if (redis != null) {
	        redis.close();
	    }
	}
	
	@PreDestroy
	public void destroy(){
		pool.destroy();
	}
	 
	public Jedis borrow(){
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis;
        } catch (Exception e) {
            //释放redis对象
            pool.close();
            e.printStackTrace();
        } 
        return null;
	}

	public void setRedisSwitch(boolean redisSwitch) {
		this.redisSwitch = redisSwitch;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

}
