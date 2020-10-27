/**
 * Copyright (C) 2016 Juno Inc., All Rights Reserved.
 */
package com.jyl.common;

/**
 * 
 * @author Long, E-mail:jyl0401@163.com
 * @date 2016年6月20日 下午3:34:09
 */
public class Result {
	
	private Integer code;
	
	private String msg;
	
	/**
	 * 默认初始化值
	 */
	public Result(){
		this.code = 0;
		this.msg = "success";
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
