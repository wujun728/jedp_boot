/**
 * 
 */
package com.zhang.test;

import java.util.Date;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhang.model.User;
import com.zhang.service.UserServiceI;

/**
 * @author Wujun
 * @date  2013-7-17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
public class Test {

	
	private UserServiceI userService;
	public UserServiceI getUserService() {
		return userService;
	}
	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}




	@org.junit.Test
	public void testAdd(){
	User user = new User();
		user.setName("zhanghao");
		user.setBirthday(new Date());
		user.setEmail("jack_rab@126.com");
		user.setTel("13888889999");
		userService.insert(user);
	}
	
	
}
