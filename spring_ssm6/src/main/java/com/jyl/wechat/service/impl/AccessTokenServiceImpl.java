/**
 * Copyright (C) 2016 Juno Inc., All Rights Reserved.
 */
package com.jyl.wechat.service.impl;

import java.text.MessageFormat;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jyl.util.httpClient.HttpClientUtil;
import com.jyl.util.json.JsonUtil;
import com.jyl.util.redis.JedisService;
import com.jyl.wechat.WechatConst;
import com.jyl.wechat.dto.AccessTokenResponseDTO;
import com.jyl.wechat.service.AccessTokenService;

import redis.clients.jedis.Jedis;

/**
 *
 * @author Long, E-mail:jyl0401@163.com
 * @date 2016年8月1日 上午9:24:53
 */
@Service
public class AccessTokenServiceImpl implements AccessTokenService {

	@Autowired
	private JedisService redisService;
	
	@Value("${wechat.app.id}")
	private String appId;
	
	@Value("${wechat.app.secret}")
	private String appSecret;
	
	@Value("${wechat.app.accessToken.baseUrl}")
	private String baseUrl;
	
	Logger log = Logger.getLogger(AccessTokenServiceImpl.class);
	
	@Override
	public String obtain() {

		String accessToken = "";
		String url = MessageFormat.format(baseUrl, appId, appSecret);
		String responseStr = HttpClientUtil.get(url);
		Jedis jedis = redisService.borrow();
		
		try {
			if(jedis.exists(WechatConst.WECHAT_ACCESS_TOKEN)){
				accessToken = jedis.get(WechatConst.WECHAT_ACCESS_TOKEN);
			}else{
				AccessTokenResponseDTO dto = JsonUtil.json2Bean(responseStr, AccessTokenResponseDTO.class);
				accessToken = dto.getAccess_token();
				jedis.setex(WechatConst.WECHAT_ACCESS_TOKEN, 7000, accessToken);
			}
		} catch (Exception e) {
			log.debug("[wechat]accessToken response parse error[response="+responseStr+"]");
			e.printStackTrace();
		} finally{
			redisService.returnResource(jedis);
		}
		return accessToken;
	}
	
	public void setAppId(String appId) {
		this.appId = appId;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	
}
