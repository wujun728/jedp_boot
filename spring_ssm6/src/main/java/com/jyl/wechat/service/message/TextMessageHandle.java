/**
 * Copyright (C) 2016 Juno Inc., All Rights Reserved.
 */
package com.jyl.wechat.service.message;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Long, E-mail:jyl0401@163.com
 * @date 2016年8月5日 上午10:02:08
 */
public class TextMessageHandle extends AbstractMessageHandle{

	@Override
	public String handle(Map<String, String> map) {
		
		//开发者微信号
        String toUserName = map.get("ToUserName");
        
		//发送方帐号
        String fromUserName = map.get("FromUserName");
        
        //TODO log
        
        return MessageFormat.format(MessageTemplate.RESPONSE_TEXT_MSG_TMPL, 
        		fromUserName, toUserName, new Date(), MessageTemplate.DEFAULT_RESPONSE_TEXT_CONTENT);
        
	}
}
