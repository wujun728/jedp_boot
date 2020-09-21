package com.ssm.test;

import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssm.manager.UserService;
import com.ssm.manager.pojo.User;
public class ssmTested {
	private ApplicationContext ctx=null;
	private UserService userService=null;
	@Before
	public void aaaa() {
		System.out.println("starting...");
	}
	
	@Test
	public void testInsertUser() {
		ctx=new ClassPathXmlApplicationContext("spring/applicationContext-service.xml");
		userService = ctx.getBean(UserService.class);
		User user2 = new User(9,"唐僧","d2dd35fddft6r");
		userService.insertUser(user2);
	
	}
	
	@Test
	public void testGetUsers() {
		ctx=new ClassPathXmlApplicationContext("spring/applicationContext-service.xml");
		userService = ctx.getBean(UserService.class);
		List<User> users = userService.getUsers();
		for (User us : users) {
			System.out.println(us);
		}
	}
	
	@Test
	public void testDeleteUser() {
		ctx=new ClassPathXmlApplicationContext("spring/applicationContext-service.xml");
		userService = ctx.getBean(UserService.class);
		userService.deleteUser(6);
	}
	
			
}
