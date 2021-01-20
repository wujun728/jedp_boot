package com.java1234.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.java1234.entity.User;
import com.java1234.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class UserAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;

	@Resource
	private UserService userService;
	
	private User user;
	private String error;
	
	
	
	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public String getError() {
		return error;
	}



	public void setError(String error) {
		this.error = error;
	}


	public String login()throws Exception{
		HttpSession session=request.getSession();
		User currentUser=userService.findUserByNameAndPassword(user);
		if(currentUser!=null){
			session.setAttribute("currentUser", currentUser);
			return SUCCESS;
		}else{
			error="用后名或者密码错误！";
			return ERROR;
		}
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}

}
