package com.jyl.security.userdetails;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.jyl.system.user.model.User;
import com.jyl.system.user.service.UserService;

/**
 * 
 * @author Long, E-mail:jyl0401@163.com
 * @date 2016年5月30日 下午2:43:53
 */
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserService userService;
	
	Logger log = Logger.getLogger(MyUserDetailsService.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userService.getUserByUsername(username);
		if(user == null){
			log.debug("username["+username+"] is not exists.");
			throw new UsernameNotFoundException("用户名不存在");
			//return null;
		}
		log.debug("username["+user.getUsername()+"].");
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		list.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		UserDetails userDetails = (MyUserDetails)new MyUserDetails(user.getId(), username, user.getPassword(), list);
		return userDetails;
	}

}
