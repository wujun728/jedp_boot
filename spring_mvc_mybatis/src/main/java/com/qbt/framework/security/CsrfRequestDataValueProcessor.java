/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.security</p>
 * <p>File: CsrfRequestDataValueProcessor.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年8月26日-下午5:57:43</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.security;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.support.RequestDataValueProcessor;

/**<p>Class: CsrfRequestDataValueProcessor.java</p>
 * <p>Description: 表单渲染</p>
 * <pre>
 *     form:form 表单渲染
 * </pre>
 * @author 鲍建明
 * @date 2015年8月26日 下午5:57:43
 * @version 1.0.0
 */
public class CsrfRequestDataValueProcessor implements RequestDataValueProcessor {
	
	
	private CsrfTokenManager csrfTokenManager;
	

	public void setCsrfTokenManager(CsrfTokenManager csrfTokenManager) {
		this.csrfTokenManager = csrfTokenManager;
	}

	public Map<String, String> getExtraHiddenFields(HttpServletRequest request) {
		//此处是当使用spring的taglib标签<form:from>创建表单时候，增加的隐藏域参数
		Map<String, String> hiddenFields = new HashMap<String, String>();
		hiddenFields.put(CsrfTokenManager.CSRF_PARAM_NAME, csrfTokenManager.createTokenForRequest(request));
		return hiddenFields;
	}

	public String processAction(HttpServletRequest request, String action) {
		//TODO 暂时原样返回action
		return action;
	}

	public String processFormFieldValue(HttpServletRequest request, String name,
			String value, String type) {
		// TODO 暂时原样返回value
		return value;
	}

	public String processUrl(HttpServletRequest request, String url) {
		// TODO 暂时原样返回url
		return url;
	}

	@Override
	public String processAction(HttpServletRequest arg0, String action,
			String arg2) {
		// TODO Auto-generated method stub
		return action;
	}

}
