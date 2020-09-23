package com.jun.plugin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {

	@RequestMapping("/helloWorld")
	public String helloWorld(Model model){
		model.addAttribute("message", "StringMvc helloWorld  ");
		return "helloWorld.jsp";
	}
}
