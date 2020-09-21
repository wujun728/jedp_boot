/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.emun</p>
 * <p>File: CommonEmun.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年8月26日-下午5:57:43</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.emun;

/**<p>Class: CommonEmun.java</p>
 * <p>Description: 通用枚举类</p>
 * <pre>
 *       记录通用的一些错误信息
 *       200 -- 成功
 *       300 -- 失败  
 * </pre>
 * @author 鲍建明
 * @date 2015年8月26日 下午5:57:43
 * @version 1.0.0
 */
public enum CommonEmun implements BusinessCode{
	
	SUCCESS(200, "成功"),
	ERROR(500, "失败"),
	PARAMES_ERROR(501, "参数异常")
	;
	
	private CommonEmun(Integer code, String message){
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
