/**
 * Copyright (C) 2016 Juno Inc., All Rights Reserved.
 */
package com.jyl.security.userdetails;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author Long, E-mail:jyl0401@163.com
 * @date 2016年6月20日 上午11:00:24
 */
public class MyUserDetails extends User{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2822359065805474103L;
	
	Long id;

	public MyUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public MyUserDetails(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {

		super(username, password, true, true, true, true, authorities);
		this.id = id;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
