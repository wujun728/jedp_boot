/**
 * Copyright (C) 2016 Juno Inc., All Rights Reserved.
 */
package com.jyl.system.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jyl.util.randomCode.RandomCodeUtils;

/**
 *
 * @author Long, E-mail:jyl0401@163.com
 * @date 2016年7月12日 下午9:30:59
 */
@Controller
@RequestMapping("/validateCode")
public class ValidateCodeController {
	
	@RequestMapping("/create")
	public void redis(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response){
		
		RandomCodeUtils.createRandomCode(request, response);
	}

}
