package cn.edu.nuc.Store.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.edu.nuc.Store.model.Admin;
import cn.edu.nuc.Store.services.impl.AdminServer;

@Controller
public class AdminController {
	@Autowired
	public AdminServer adminServer;
	@RequestMapping(value="/adminlogin",method=RequestMethod.GET)
	public String adminlogin(){
		return "/admin/adminlogin";
	}
	@RequestMapping(value="/adminlogindo",method=RequestMethod.POST)
	public String adminlogindo(Admin admin,HttpSession session,Model model){
		if(adminServer.select(admin.getAdminname()).getAdminpass().equals(admin.getAdminpass())){
			session.setAttribute("admin",adminServer.select(admin.getAdminname()));
			return "forward:/goodlist2";
		}else{
			model.addAttribute("msg", "用户名密码错误，请稍后再试！");
			return "forward:WEB-INF/views/admin/adminlogin.jsp";
		}
	}
}
