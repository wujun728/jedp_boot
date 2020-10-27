package com.wuzy.core.shiro;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * SessionManager 这里默认把session数据存储在redis的db1中
 * @author wuzy
 * 2017年1月2日 下午5:03:28
 */
public class SessionManager extends DefaultWebSessionManager {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private RedisManager redisManager;

	protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
		logger.info("start SessionManager getSessionId >>>");
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		String equipment_id = servletRequest.getHeader("equitment_id");
		logger.info("start SessionManager getSessionId  equipment_id:"+equipment_id);
		if (StringUtils.isNotBlank(equipment_id)) {

			try {
				Object o = redisManager.get(equipment_id, 1);

				if (o == null) {
					return null;
				}

				return o.toString();

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return super.getSessionId(request, response);
	}
	protected Session doCreateSession(SessionContext context) {
		logger.info("start SessionManager doCreateSession >>>");
        Session session = super.doCreateSession(context);


        HttpServletRequest request = WebUtils.getHttpRequest(context);
        String equipment_id = request.getHeader("equitment_id");
        logger.info("start SessionManager doCreateSession  equipment_id:"+equipment_id);

        if (StringUtils.isNotBlank(equipment_id)) {

            try {
                redisManager.set(equipment_id, session.getId(), 1);
                return session;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        return session;
    }

    public RedisManager getRedisManager() {
        return redisManager;
    }

    public void setRedisManager(RedisManager redisManager) {
        this.redisManager = redisManager;
    }
	

}
