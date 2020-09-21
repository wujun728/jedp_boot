/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.emun</p>
 * <p>File: FileOperateEmun.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年8月26日-下午5:57:43</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.emun;


/**<p>Class: FileOperateEmun.java</p>
 * <p>Description: 文件操作枚举类</p>
 * <pre>
 *		说明文件操作时产生的异常说明
 *    	300100--300500 异常代码
 * </pre>
 * @author 鲍建明
 * @date 2015年8月26日 下午5:57:43
 * @version 1.0.0
 */
public enum FileOperateEmun implements BusinessCode {
	
	
	FILE_NOT_EXITS(300100, "文件不存在或者文件不正确"),
	FILE_NAME_NULL(300101, "文件名不能为空")
	;
	
	
	
	private FileOperateEmun(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	private Integer code;
	private String message;
	
	@Override
	public Integer getCode() {
		return this.code;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	
}
