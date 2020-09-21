/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.exception</p>
 * <p>File: BusinessException.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年8月26日-下午5:57:43</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.exception;

import com.qbt.framework.emun.BusinessCode;
import com.qbt.framework.emun.CommonEmun;

/**<p>Class: BusinessException.java</p>
 * <p>Description: 业务异常类</p>
 * <pre>
 *       业务中的异常类
 * </pre>
 * @author 鲍建明
 * @date 2015年8月26日 下午5:57:43
 * @version 1.0.0
 */
public class BusinessRuntimeException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer code;
	private String message;
	
	
	public BusinessRuntimeException(BusinessCode businessCode){
		this.code = businessCode.getCode();
		this.message = businessCode.getMessage();
	}
	
	public BusinessRuntimeException(Integer code, String messge){
		this.code = code;
		this.message = messge;
	}
	
	public BusinessRuntimeException(String message){
		this.code = CommonEmun.ERROR.getCode();
		this.message = message;
	}
	
	
	public Integer getCode(){
		return this.code;
	}
	
	public String getMessage(){
		return this.message;
	}
	
}
