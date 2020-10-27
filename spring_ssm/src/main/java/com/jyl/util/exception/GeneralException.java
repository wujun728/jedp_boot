/**
 * Copyright (C) 2016 Juno Inc., All Rights Reserved.
 */
package com.jyl.util.exception;

/**
 * 通用运行时异常
 * @author Long, E-mail:jyl0401@163.com
 * @date 2016年6月1日 下午2:08:43
 */
public class GeneralException extends RuntimeException{

	private static final long serialVersionUID = -1298202411132096604L;
	
	//用来存放相应的异常编码，例如USER_NOT_FOUND
	private String code;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public GeneralException(String code, String msg){
		super(msg);
		this.code = code;
	}

	public GeneralException(String code, String msg, Throwable t) {
		super(msg,t);
		this.code = code;
	}
}
