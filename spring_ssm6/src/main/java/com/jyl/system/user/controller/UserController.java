package com.jyl.system.user.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jyl.common.Result;
import com.jyl.security.SecurityUtils;
import com.jyl.system.user.model.User;
import com.jyl.system.user.service.UserService;
import com.jyl.util.ParamChecker;
import com.jyl.util.servlet.HttpServletUtil;

/**
 * 
 * @author Long, E-mail:jyl0401@163.com
 * @date 2016年5月30日 下午2:43:53
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {
	
	Logger log = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * 返回用户界面
	 * @param modelMap
	 * @param userId
	 * @return
	 */
	@RequestMapping("/showInfo/{userId}")
	public String showInfo(ModelMap modelMap, @PathVariable Long userId){
		User user = userService.getUserById(userId);
		modelMap.addAttribute("user", user);
		return "/user/showInfo";
	}
	
	/**
	 * 根据用户ID查询用户信息
	 * @param modelMap
	 * @param userId
	 * @return
	 */
	@RequestMapping("/{userId}")
	public void findOne(HttpServletResponse response, ModelMap modelMap, @PathVariable Long userId) throws IOException {
		
		User user = userService.getUserById(userId);
		HttpServletUtil.writeObjectJSON2Response(response, user);
	}
	
	@RequestMapping("/list")
	public void findAll(HttpServletResponse response, ModelMap modelMap) throws IOException {
		
		List<User> list = userService.getUserList();
		HttpServletUtil.writeObjectJSON2Response(response, list);
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public void createOne (HttpServletResponse response, ModelMap modelMap, User user) throws IOException {
		
		if(user == null){
			log.error("param user cannot be null.");
			return;
		}
		
		userService.insertOne(user);
	}
	
	@RequestMapping(value="/edit/{userId}", method=RequestMethod.POST)
	public void editOne (HttpServletResponse response, ModelMap modelMap, 
			@PathVariable Long userId, User user) throws IOException {
		
		if(userId == null || user == null){
			log.error("param userId or user cannot be null.");
			return;
		}
		
		user.setId(userId);
		userService.editOne(user);
	}
	
	@RequestMapping(value="/pwdModify", method=RequestMethod.POST)
	public void editOne (HttpServletResponse response, ModelMap modelMap, 
			String oldPassword, String newPassword, String confirmPassword) throws IOException {
		
		Result result = new Result();
		ParamChecker.checkEmpty(oldPassword, "oldPassword");
		ParamChecker.checkEmpty(newPassword, "newPassword");
		ParamChecker.checkEmpty(confirmPassword, "confirmPassword");
		
		result = userService.pwdModify(SecurityUtils.getCurrentUserId(), oldPassword, newPassword, confirmPassword);
		HttpServletUtil.writeObjectJSON2Response(response, result);
	}
	
}
