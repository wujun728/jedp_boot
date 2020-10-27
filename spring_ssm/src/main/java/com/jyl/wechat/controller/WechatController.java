/**
 * Copyright (C) 2016 Juno Inc., All Rights Reserved.
 */
package com.jyl.wechat.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jyl.wechat.service.WechatService;
import com.jyl.wechat.util.WechatUtil;

/**
 *
 * @author Long, E-mail:jyl0401@163.com
 * @date 2016年7月29日 下午4:20:09
 */
@Controller
@RequestMapping("/wechat")
public class WechatController {
	
	private final String TOKEN = "long0824";
	
	@Autowired
	private WechatService wechatService;
	
	@RequestMapping("/token")
	public void token(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws IOException{

		//微信加密签名signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
		String signature = request.getParameter("signature");
		
		//时间戳
		String timestamp = request.getParameter("timestamp");
		
		//随机数
		String nonce = request.getParameter("nonce");
		
		//随机字符串
		String echostr = request.getParameter("echostr");
		
		//排序
		String sortString = WechatUtil.sort(TOKEN, timestamp, nonce);
		
		//加密
		String mySignature = WechatUtil.sha1(sortString);
		
		//校验签名
		if (mySignature != null && mySignature != "" && mySignature.equals(signature)) {
			System.out.println("success");
			//如果检验成功输出echostr，微信服务器接收到此输出，才会确认检验完成。
			response.getWriter().write(echostr);
		} else {
			System.out.println("fail");
		}
	}
	
	@RequestMapping(value="/token", method=RequestMethod.POST)
	public void controller(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		String responseMsg = wechatService.messageHandle(request);
		response.getWriter().write(responseMsg);
	}
	
}
