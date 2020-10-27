package me.kafeitu.activiti.chapter7.service;

import me.kafeitu.activiti.chapter7.entity.Leave;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 请假流程Service
 *
 * @author henryyan
 */
@Service
@Transactional
public class LeaveWorkflowService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    LeaveManager leaveManager;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    /**
     * 保存请假实体并启动流程
     */
    public ProcessInstance startWorkflow(Leave entity, String userId, Map<String, Object> variables) {
        if (entity.getId() == null) {
            entity.setApplyTime(new Date());
            entity.setUserId(userId);
        }
        leaveManager.save(entity);
        String businessKey = entity.getId().toString();

        // 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
        identityService.setAuthenticatedUserId(userId);

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leave", businessKey, variables);
        String processInstanceId = processInstance.getId();
        entity.setProcessInstanceId(processInstanceId);
        logger.debug("start process of {key={}, bkey={}, pid={}, variables={}}", new Object[]{"leave", businessKey, processInstanceId, variables});
        leaveManager.save(entity);
        return processInstance;
    }

    /**
     * 查询待办任务
     */
    @Transactional(readOnly = true)
    public List<Leave> findTodoTasks(String userId) {
        List<Leave> results = new ArrayList<Leave>();
        List<Task> tasks = new ArrayList<Task>();

        // 根据当前人(受理人)已签收的ID查询
        List<Task> todoList = taskService.createTaskQuery().processDefinitionKey("leave").taskAssignee(userId).active().list();//activiti查询签收任务（签收之后变成Assignee）
 
        // 根据当前人(候选人)未签收的任务
        List<Task> unsignedTasks = taskService.createTaskQuery().processDefinitionKey("leave").taskCandidateUser(userId).active().list();//activiti查询未签收任务（未签收之前是CandidateUser）

        // 合并
        tasks.addAll(todoList);
        tasks.addAll(unsignedTasks);

        // 根据流程的业务ID查询实体并关联
        for (Task task : tasks) {
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();//ACT_RU_TASK 
            Leave leave = leaveManager.get(new Long(processInstance.getBusinessKey()));//act_ru_execution  对于自定义表单来说启动的时候会传入businessKey作为业务和流程的关联属性
            leave.setTask(task);
            leave.setProcessInstance(processInstance);// 运行中的流程实例ACT_RU_EXECUTION 就是一个Execution
            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processInstance.getProcessDefinitionId()).singleResult();
            leave.setProcessDefinition(processDefinition);// 流程定义 一个bpm leave:1:31 对象信息
            results.add(leave);
        }
        return results;
    }

    public void complete(Leave leave, Boolean saveEntity, String taskId, Map<String, Object> variables) {
        if (saveEntity) {
            leaveManager.save(leave);
        }
        taskService.complete(taskId, variables);
    }

}
