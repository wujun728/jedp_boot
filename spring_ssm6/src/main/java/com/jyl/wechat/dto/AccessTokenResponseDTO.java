/**
 * Copyright (C) 2016 Juno Inc., All Rights Reserved.
 */
package com.jyl.wechat.dto;

/**
 *
 * @author Long, E-mail:jyl0401@163.com
 * @date 2016年8月1日 下午8:46:41
 */
public class AccessTokenResponseDTO {
	
	private String access_token;
	
	private String expires_in;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
	
}
