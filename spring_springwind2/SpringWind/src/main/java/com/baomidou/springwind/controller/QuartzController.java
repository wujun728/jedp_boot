package com.baomidou.springwind.controller;

import java.text.ParseException;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.kisso.annotation.Permission;
import com.baomidou.springwind.service.IQuartzService;

/**
 * <p>
 * Quartz管理
 * </p>
 *
 * @author yyn_0210@sina.com
 * @Date 2016-04-19
 */
@Controller
@RequestMapping("/quartz")
public class QuartzController extends BaseController {

    @Autowired
    private IQuartzService quartzService;

    /**
     * 发送
     */
    @Permission("1001")
    @RequestMapping("/add")
    public String addJob(Model model, String jobName) throws ParseException, SchedulerException {
		if (isPost()) {
			model.addAttribute("jobName", jobName);
			model.addAttribute("loginName", getSSOToken().getData());
			boolean rlt = quartzService.addDemoJob(jobName);
			String tipMsg = "名称为【" + jobName + "】的Job 已存在！！";
			if(rlt){
				tipMsg = "添加JOb成功！！";
			}
			model.addAttribute("tipMsg", tipMsg);
		}

        return "/quartz/add";

    }

}
