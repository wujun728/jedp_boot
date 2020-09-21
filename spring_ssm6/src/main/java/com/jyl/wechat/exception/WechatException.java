/**
 * Copyright (C) 2016 Juno Inc., All Rights Reserved.
 */
package com.jyl.wechat.exception;

/**
 *
 * @author Long, E-mail:jyl0401@163.com
 * @date 2016年8月5日 上午10:27:43
 */
public class WechatException extends RuntimeException{

	private static final long serialVersionUID = -1298202411132096604L;
	
	//用来存放相应的异常编码，例如USER_NOT_FOUND
	private String code;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public WechatException(String code, String msg){
		super(msg);
		this.code = code;
	}

	public WechatException(String code, String msg, Throwable t) {
		super(msg,t);
		this.code = code;
	}

}
