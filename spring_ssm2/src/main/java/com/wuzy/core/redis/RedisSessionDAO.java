package com.wuzy.core.redis;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RedisSessionDAO 操作
 * @author wuzy
 * 2016年12月18日 下午8:50:31
 */
public class RedisSessionDAO extends AbstractSessionDAO{
	private static Logger logger =LoggerFactory.getLogger(RedisSessionDAO.class);
	
	/**
	 * shiro-redis Session管理
	 */
	private RedisManager redisManager;
	/**
	 * The Redis key prefix for the sessions： session前缀
	 */
	private String keyPrefix="wu_shiro_redis_session:";
	
	public void update(Session session) throws UnknownSessionException{
		this.saveSession(session);
	}
	/**
	 * save session
	 * @param session
	 */
	private void saveSession(Session session) {
		if(session==null || session.getId()==null){
			logger.error("session or sessionId is null>>>");
			return;
		}
		byte[] key =getByteKey(session.getId());
		byte[] value = SerializeUtils.serialize(session);
		session.setTimeout(redisManager.getExpire()*1000);//有效期
		this.redisManager.set(key, value, redisManager.getExpire());
		
	}
	
	/**
	 * del
	 */
	public void delete(Session session){
		if(session==null || session.getId()==null){
			logger.error("session or session id is null>>>");
		}
		redisManager.del(this.getByteKey(session.getId()));
	}
	
	public Collection<Session> getActiveSessions() {
		Set<Session> sessions = new HashSet<Session>();
		
		Set<byte[]> keys = redisManager.keys(this.keyPrefix + "*");
		if(keys != null && keys.size()>0){
			for(byte[] key:keys){
				Session s = (Session)SerializeUtils.deserialize(redisManager.get(key));
				sessions.add(s);
			}
		}
		
		return sessions;
	}
	
	protected Serializable doCreate(Session session) {
		Serializable sessionId = this.generateSessionId(session);  
        this.assignSessionId(session, sessionId);
        this.saveSession(session);
		return sessionId;
	}
	/**
	 * 从Redis中读取Session，并重置过期时间
	 * 
	 * TODO 这里对Session缓存操作
	 */
	protected Session doReadSession(Serializable sessionId) {
		if(sessionId == null){
			logger.error("session id is null");
			return null;
		}
		
		Session s = (Session)SerializeUtils.deserialize(redisManager.get(this.getByteKey(sessionId)));
		return s;
	}
	
	private byte[] getByteKey(Serializable sessionId){
		String preKey = this.keyPrefix+sessionId;
		return preKey.getBytes();
	}
	

	public RedisManager getRedisManager() {
		return redisManager;
	}

	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;
	}

	public String getKeyPrefix() {
		return keyPrefix;
	}

	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}
	
	
	
	
	
}
