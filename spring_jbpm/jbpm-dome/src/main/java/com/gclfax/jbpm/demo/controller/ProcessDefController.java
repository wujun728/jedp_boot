package com.gclfax.jbpm.demo.controller;

import com.gclfax.jbpm.demo.domain.Business1;
import com.gclfax.jbpm.demo.domain.Business1ReviewLog;
import com.gclfax.jbpm.demo.service.BusinessService;
import net.sf.json.JSONObject;
import org.jbpm.services.api.DefinitionService;
import org.jbpm.services.api.ProcessService;
import org.jbpm.services.api.RuntimeDataService;
import org.jbpm.services.api.model.ProcessDefinition;
import org.jbpm.services.api.model.ProcessInstanceDesc;
import org.kie.api.task.TaskService;
import org.kie.internal.query.QueryContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@RestController
@RequestMapping("/processdef")
public class ProcessDefController {

    @Autowired
    private RuntimeDataService runtimeDataService;

    @Autowired
    private ProcessService processService;

    @Autowired
    private BusinessService businessService;

    @Autowired
    private DefinitionService definitionService;



    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public Collection<ProcessDefinition> getProcessDef() {
        Collection<ProcessDefinition> processDefinitions = runtimeDataService.getProcesses(new QueryContext(0, 100));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ProcessdefList");
        modelAndView.addObject("processDefinitions", processDefinitions);
        return processDefinitions;

    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    @ResponseBody
    public ProcessDefinition getProcessDefinition(@RequestParam String deployment, @RequestParam String id) {

        ProcessDefinition definition = runtimeDataService.getProcessesByDeploymentIdProcessId(deployment, id);

        return definition;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public String newProcessInstance(@RequestParam String deploymentId, @RequestParam String processId) {

        Map<String, Object> map = new HashMap<>();
        try {

            map.put("day",1);

            long processInstanceId = processService.startProcess(deploymentId, processId,map);


            List<Long> tids = runtimeDataService.getTasksByProcessInstanceId(processInstanceId);


            map.put("code", 1);
            map.put("msg", "创建成功 实例ID：" + processInstanceId + " 任务ID:" + tids);
            return JSONObject.fromObject(map).toString();

        } catch (Exception e) {
            map.put("msg", e.getMessage());
            return JSONObject.fromObject(map).toString();
        }

    }

    @RequestMapping(value = "/processdefAdd")
    public ModelAndView addProcessdef(@RequestParam String id, @RequestParam String name, @RequestParam String deploymentId) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ProcessdefAdd");
        modelAndView.addObject("id", id);
        modelAndView.addObject("name", name);
        modelAndView.addObject("deploymentId", deploymentId);

        return modelAndView;
    }

    protected String getAuthUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = auth.getName();

        return userId;
    }


}
