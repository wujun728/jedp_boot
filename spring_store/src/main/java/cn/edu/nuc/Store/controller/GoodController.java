package cn.edu.nuc.Store.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GoodController {
	@RequestMapping(value="/goodlist",method=RequestMethod.GET)
	public String goodlist(HttpSession session){
		if(session.getAttribute("admin")==null){
			return "forward:/adminlogin";
		}else{
			return "/good/goodList";
		}
	}
	@RequestMapping(value="/goodlist2",method=RequestMethod.POST)
	public String goodlist2(HttpSession session){
		if(session.getAttribute("admin")==null){
			return "forward:/adminlogin";
		}else{
			return "/good/goodList";
		}
	}
}
