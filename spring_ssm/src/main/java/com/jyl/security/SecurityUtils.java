/**
 * Copyright (C) 2016 Juno Inc., All Rights Reserved.
 */
package com.jyl.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.jyl.system.user.model.User;

/**
 *
 * @author Long, E-mail:jyl0401@163.com
 * @date 2016年6月7日 上午10:34:12
 */
public class SecurityUtils {
	
	/**
	 * 获取当前登录用户信息
	 */
	public static User getCurrentUserInfo() throws ClassCastException {
		Authentication auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getPrincipal() instanceof User) {
			return (User) auth.getPrincipal();
		} else {
			return null;
		}
	}

	/**
	 * 获取当前登录用户ID
	 */
	public static Long getCurrentUserId() {
		User user = getCurrentUserInfo();
		return user == null ? null : user.getId();
	}

}
