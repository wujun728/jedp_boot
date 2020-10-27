/**
 * Copyright (C) 2016 Juno Inc., All Rights Reserved.
 */
package com.jyl.wechat.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jyl.wechat.dto.MessageTypeEnum;
import com.jyl.wechat.exception.WechatException;
import com.jyl.wechat.service.WechatService;
import com.jyl.wechat.service.message.MessageContext;
import com.jyl.wechat.service.message.TextMessageHandle;
import com.jyl.wechat.util.WechatUtil;

/**
 *
 * @author Long, E-mail:jyl0401@163.com
 * @date 2016年8月5日 上午10:17:41
 */
@Service
public class WechatServiceImpl implements WechatService {
	
	Logger log = Logger.getLogger(WechatServiceImpl.class);

	@Override
	public String messageHandle(HttpServletRequest request) {

		String responseMsg = "";
		Map<String,String> map = new HashMap<String,String>();
		try {
			map = WechatUtil.parseXml(request);
		} catch (Exception e) {
			log.error("[wechat]parse request xml error.",e);
			e.printStackTrace();
		}
		
		String msgType = map.get("MsgType").toString();
		if(!StringUtils.hasText(msgType)){
			log.error("[wechat]msgType cannot be null or empty.");
			throw new WechatException("MSGTYPE_EMPTY","msgType cannot be null or empty.");
		}
		
		MessageTypeEnum messageEnumType = MessageTypeEnum.valueOf(MessageTypeEnum.class, msgType.toUpperCase());
        if(MessageTypeEnum.TEXT.equals(messageEnumType)){
			MessageContext message = new MessageContext(new TextMessageHandle());
			responseMsg = message.handle(map);
		}
		
		return responseMsg;
	}

}
