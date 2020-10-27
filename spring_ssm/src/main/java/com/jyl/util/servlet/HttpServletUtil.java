/**
 * Copyright (C) 2016 Juno Inc., All Rights Reserved.
 */
package com.jyl.util.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.jyl.util.json.JsonUtil;

/**
 * 
 * @author Long, E-mail:jyl0401@163.com
 * @date 2016年5月30日 下午2:43:53
 */
public class HttpServletUtil {

	/**
	 * 将对象转换成JSON格式的数据，返回到客户端。
	 */
	public static void writeObjectJSON2Response(HttpServletResponse response,
			Object object) throws IOException {

		setResponseInfo(response);
		response.getWriter().print(JsonUtil.bean2Json(object));

	}
	
	private static void setResponseInfo(HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		// 不能使用application/json,在IE下会提示用户下载
		//response.setContentType("application/json");
		response.setContentType("text/html");
	}
}
