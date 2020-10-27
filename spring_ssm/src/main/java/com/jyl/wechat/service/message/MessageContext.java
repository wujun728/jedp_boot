/**
 * Copyright (C) 2016 Juno Inc., All Rights Reserved.
 */
package com.jyl.wechat.service.message;

import java.util.Map;

/**
 *
 * @author Long, E-mail:jyl0401@163.com
 * @date 2016年8月5日 上午10:05:23
 */
public class MessageContext {
	
	private AbstractMessageHandle messageHandle;
	
	public MessageContext(AbstractMessageHandle messageHandle){
		
		this.messageHandle = messageHandle;
	}
	
	public String handle(Map<String,String> map){
		
		return messageHandle.handle(map);
	}

}
