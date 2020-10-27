/**
 * Copyright (C) 2016 Juno Inc., All Rights Reserved.
 */
package com.jyl.wechat.service.message;

/**
 *
 * @author Long, E-mail:jyl0401@163.com
 * @date 2016年8月5日 上午10:51:07
 */
public class MessageTemplate {
	
	public final static String RESPONSE_TEXT_MSG_TMPL = "<xml>"
			+ "<ToUserName><![CDATA[{0}]]></ToUserName>"
			+ "<FromUserName><![CDATA[{1}]]></FromUserName>"
			+ "<CreateTime>{2}</CreateTime>"
			+ "<MsgType><![CDATA[text]]></MsgType>"
			+ "<Content><![CDATA[{3}]]></Content>"
			+ "</xml>";

	public final static String DEFAULT_RESPONSE_TEXT_CONTENT = "Hello,world!";
}
