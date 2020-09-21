/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.exception</p>
 * <p>File: FileOperateException.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年8月26日-下午5:57:43</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.exception;

import com.qbt.framework.emun.BusinessCode;


/**<p>Class: FileOperateException.java</p>
 * <p>Description: 文件操作异常</p>
 * <pre>
 *       文件操作异常
 * </pre>
 * @author 鲍建明
 * @date 2015年8月26日 下午5:57:43
 * @version 1.0.0
 */
public class FileOperateException extends BusinessException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3164049842495737965L;
	
	public FileOperateException(BusinessCode businessCode) {
		super(businessCode);
	}

	public FileOperateException(Integer code, String message) {
		super(code, message);
	}
	
	/**
	 * 
	 * <p>构造器：使用通用的失败代码</p>
	 * <pre>
	 *    
	 * </pre>
	 * @param message
	 */
	public FileOperateException(String message) {
		super(message);
	}
	
}
