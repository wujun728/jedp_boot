package com.vonchange.headb.spring.template.impl;

import org.springframework.dao.DataAccessException;

@SuppressWarnings("serial")
public class SpringJdbcDataAccessException extends DataAccessException {

	public SpringJdbcDataAccessException(String msg) {
		super(msg);
	}

	public SpringJdbcDataAccessException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
