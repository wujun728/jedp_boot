/**
 * Copyright (C) 2016 Juno Inc., All Rights Reserved.
 */
package com.jyl.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 *
 * @author Long, E-mail:jyl0401@163.com
 * @date 2016年7月14日 下午9:40:44
 */
public class ValidateCodeAuthenticationException extends AuthenticationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2098535363991753260L;

	public ValidateCodeAuthenticationException(String msg) {
		super(msg);
	}
	
	public ValidateCodeAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

}
