/**
 * CCC 2013-2013 All Rights Reserved.
 */
package com.ipeony.ssm.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipeony.ssm.dao.IDataManagerMapper;
import com.ipeony.ssm.entity.User;

/**
 * 
 * 功能描述：项目介绍
 * 
 * @author 作者 13071472
 * @version 1.0.0
 * @date 2013年11月6日 上午10:17:11
 */
@Controller
public class AboutController {
    
    private static final Logger logger = LoggerFactory.getLogger(AboutController.class);
    
    @Autowired
    private IDataManagerMapper dataManager;
    
	@RequestMapping("about")
	public String aboutPage() {
	    User user = dataManager.getUser("1");
	    logger.debug(user.toString());
		return "about.ftl";
	}
}
