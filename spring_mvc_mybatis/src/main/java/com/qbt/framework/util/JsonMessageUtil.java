/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.util</p>
 * <p>File: JsonMessageUtil.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年8月28日-下午2:21:33</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.util;

import com.github.pagehelper.PageInfo;
import com.qbt.framework.bean.JsonMessage;
import com.qbt.framework.emun.BusinessCode;
import com.qbt.framework.emun.CommonEmun;

/**<p>Class: JsonMessageUtil.java</p>
 * <p>Description: </p>
 * <pre>
 *         转换JsonMessag对象工具类
 * </pre>
 * @author 鲍建明
 * @date 2015年8月28日 下午2:21:33
 * @version 1.0.0
 */
public class JsonMessageUtil {
	
	/**
	 * 转换JsonMessag对象
	 * <pre></pre>
	 * @param businessCode
	 * @param pageInfo
	 * @return
	 */
	public static JsonMessage toJsonMessage(BusinessCode businessCode, PageInfo<?> pageInfo){
		return toJsonMessage(businessCode, pageInfo, null);
	}

	/**
	 * 转换JsonMessag对象
	 * <pre></pre>
	 * @param pageInfo
	 * @return
	 */
	public static JsonMessage toJsonMessage(PageInfo<?> pageInfo){
		return toJsonMessage(CommonEmun.SUCCESS, pageInfo);
	}
	
	/**
	 * 转换JsonMessag对象
	 * <pre></pre>
	 * @param pageInfo
	 * @return
	 */
	public static JsonMessage toJsonMessage(BusinessCode businessCode, Object obj){
		JsonMessage jsonMessage = new JsonMessage(businessCode.getCode(), businessCode.getMessage());
		jsonMessage.setObj(obj);
		return jsonMessage;
	}
	
	/**
	 * 转换JsonMessag对象
	 * <pre></pre>
	 * @param pageInfo
	 * @return
	 */
	public static JsonMessage toJsonMessage(BusinessCode businessCode, PageInfo<?> pageInfo, Object obj){
		JsonMessage jsonMessage = new JsonMessage(businessCode.getCode(), businessCode.getMessage());
		jsonMessage.setPageNum(pageInfo.getPageNum());
		jsonMessage.setPageSize(pageInfo.getPageSize());
		jsonMessage.setPageCount(pageInfo.getPages());
		jsonMessage.setTotal(pageInfo.getTotal());
		jsonMessage.setRows(pageInfo.getList());
		jsonMessage.setObj(obj);
		return jsonMessage;
	}
	
	/**
	 * 转换JsonMessag对象
	 * <pre></pre>
	 * @param businessCode
	 * @return
	 */
	public static JsonMessage toJsonMessage(BusinessCode businessCode){
		return new JsonMessage(businessCode.getCode(), businessCode.getMessage());
	}
	
	
}
