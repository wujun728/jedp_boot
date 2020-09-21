package cn.edu.nuc.Store.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.nuc.Store.model.User;
import cn.edu.nuc.Store.services.impl.UserServer;

@Controller
public class UserController {
	@Autowired
	private UserServer userServer;
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String register(){
		return "/user/register";
	}
	@ResponseBody
	@RequestMapping(value="/registerdo",method=RequestMethod.POST)
	public String registerdo(@RequestBody User user){
		/*System.out.println(username+"--"+userpass);*/
		if(userServer.insert(user)>0){
			return "1";
		}else{
			return "0";
		}
	}
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		return "/user/login";
	}
	@RequestMapping(value="/logindo",method=RequestMethod.POST)
	public String logindo(User user,HttpSession session,Model model){
		if(userServer.select(user.getUsername()).getUserpass().equals(user.getUserpass())){
			session.setAttribute("user",userServer.select(user.getUsername()));
			return "forward:/index.jsp";
		}else{
			model.addAttribute("msg", "用户名密码错误，请稍后再试！");
			return "forward:WEB-INF/views/user/login";
		}
	}
	@ResponseBody
	@RequestMapping(value="getuser",method=RequestMethod.GET)
	public User getuser(HttpSession session){
		User user=(User) session.getAttribute("user");
		return user;
	}
	@RequestMapping(value="cleansession",method=RequestMethod.GET)
	public String cleansession(HttpSession session){
		session.setAttribute("user", "");
		return "forward:/index.jsp";
	}
}
