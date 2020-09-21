/**
 * Copyright (C) 2016 Juno Inc., All Rights Reserved.
 */
package com.jyl.wechat.service;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Long, E-mail:jyl0401@163.com
 * @date 2016年8月5日 上午10:16:40
 */
public interface WechatService {
	
	/**
	 * 消息处理
	 * @return
	 */
	public String messageHandle(HttpServletRequest request);

}
