package com.icss.regie.workflow.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.persistence.correlation.CorrelationKeyInfo;
import org.jbpm.persistence.correlation.CorrelationPropertyInfo;
import org.junit.Before;
import org.junit.Test;
import org.kie.internal.process.CorrelationKey;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.api.model.KieContainerResource;
import org.kie.server.api.model.KieContainerResourceList;
import org.kie.server.api.model.ReleaseId;
import org.kie.server.api.model.definition.ProcessDefinition;
import org.kie.server.api.model.definition.TaskInputsDefinition;
import org.kie.server.api.model.definition.UserTaskDefinition;
import org.kie.server.api.model.definition.UserTaskDefinitionList;
import org.kie.server.api.model.instance.ProcessInstance;
import org.kie.server.api.model.instance.TaskSummary;
import org.kie.server.api.model.instance.TaskSummaryList;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.kie.server.client.ProcessServicesClient;
import org.kie.server.client.QueryServicesClient;
import org.kie.server.client.UserTaskServicesClient;

public class DummyTest {

    private static final String URL = "http://localhost:8080/kie-server/services/rest/server";
    private static final String USER = "john";
    private static final String PASSWORD = "john";
    private static final MarshallingFormat FORMAT = MarshallingFormat.JAXB;
    private String containerId = "dummy_1.0.0";
    private String processId = "dummy.first";
    private KieServicesConfiguration conf;
    private KieServicesClient kieServicesClient;

    @Before
    public void setUp() throws Exception {
        conf = KieServicesFactory.newRestConfiguration(URL, USER, PASSWORD);
        conf.setMarshallingFormat(FORMAT);
        kieServicesClient = KieServicesFactory.newKieServicesClient(conf);
    }

    @Test
    public void testUndepoly() {
        boolean deployContainer = true;
        KieContainerResourceList containers = kieServicesClient.listContainers().getResult();
        kieServicesClient.disposeContainer("regie_1.0.3");
    }

    @Test
    public void testServer() {

        boolean deployContainer = true;
        KieContainerResourceList containers = kieServicesClient.listContainers().getResult();

        // check if the container is not yet deployed, if not deploy it
        if (containers != null) {
            for (KieContainerResource kieContainerResource : containers.getContainers()) {
                System.out.println("\t######### Found container " + kieContainerResource.getContainerId() + "");
                if (kieContainerResource.getContainerId().equals(containerId)) {
                    System.out.println("\t######### Found container " + containerId + " skipping deployment...");
                    deployContainer = false;
                    break;
                }
            }
        }

        // deploy container if not there yet        
        if (deployContainer) {
            System.out.println("\t######### Deploying container " + containerId);
            KieContainerResource resource = new KieContainerResource(containerId, new ReleaseId("com.myteam", "regie", "1.0.0"));
            kieServicesClient.createContainer(containerId, resource);
        }
        //kieServicesClient.disposeContainer(containerId);

        // query for all available process definitions
        QueryServicesClient queryClient = kieServicesClient.getServicesClient(QueryServicesClient.class);
        List<ProcessDefinition> processes = queryClient.findProcesses(0, 10);
        System.out.println("\t######### Available ProcessDefinition:" + processes);
        for (ProcessDefinition process : processes) {
            System.out.println("\t######### Available ProcessDefinition:" + process);
        }

        ProcessServicesClient processClient = kieServicesClient.getServicesClient(ProcessServicesClient.class);
        // get details of process definition
        //查找 流程定义
        List<ProcessInstance> processInstancies = processClient.findProcessInstances(containerId, 0, 10);
        for (ProcessInstance processInstance : processInstancies) {
            System.out.println("\t######### Available processInstance:" + processInstance);
        }

        UserTaskDefinitionList userTaskDefinitionList = processClient.getUserTaskDefinitions(containerId, processId);

        List<UserTaskDefinition> userTaskDefinitions = userTaskDefinitionList.getItems();

        for (UserTaskDefinition userTaskDefinition : userTaskDefinitions) {
            System.out.println("\t######### Definition userTaskDefinition: " + userTaskDefinition);
        }

        ProcessDefinition definition = processClient.getProcessDefinition(containerId, processId);
        //ProcessDefinition definition = processClient.get

        System.out.println("\t######### Definition details: " + definition);

        Long processInstanceId = processClient.startProcess(containerId, processId);

        ProcessInstance processInstance = processClient.getProcessInstance(containerId, processInstanceId);

        TaskSummaryList taskSummaryList = processInstance.getActiveUserTasks();

        TaskSummary[] taskSummarys = taskSummaryList.getTasks();

        System.out.println("\t######### Instance State: " + processInstance.getState());

        UserTaskServicesClient userTasksClient = kieServicesClient.getServicesClient(UserTaskServicesClient.class);

        for (TaskSummary taskSummary : taskSummarys) {
            System.out.println("\t######### Instance taskSummary: " + taskSummary);
        }

        List<TaskSummary> mytaskSummaryList = userTasksClient.findTasks("krisv", 0, 20);

        for (TaskSummary taskSummary : mytaskSummaryList) {
            System.out.println("\t######### Instance taskSummary for me: " + taskSummary);
        }

        if (taskSummarys.length > 0) {
            System.out.println("\t######### Instance taskSummary Execute: ");
            userTasksClient.startTask(containerId, taskSummarys[0].getId(), "krisv");
            userTasksClient.completeTask(containerId, taskSummarys[0].getId(), "krisv", null);
            System.out.println("\t######### Instance taskSummary Executeed: ");
        }
        System.out.println("\t######### Instance State: " + processInstance.getState());

//          RuleServicesClient rulesClient = kieServicesClient.getServicesClient(RuleServicesClient.class);
//          KieCommands commandsFactory = KieServices.Factory.get().getCommands();
//          Command<?> insert = commandsFactory.newInsert("Some String OBJ");
//          Command<?> fireAllRules = commandsFactory.newFireAllRules();
//          Command<?> batchCommand = commandsFactory.newBatchExecution(Arrays.asList(insert, fireAllRules));
//          ServiceResponse<ExecutionResults> executeResponse = rulesClient.executeCommandsWithResults("hello", batchCommand);
//          if(executeResponse.getType() == ResponseType.SUCCESS) {
//        	  System.out.println("Commands executed with success! Response: ");
//        	  System.out.println(executeResponse.getResult());
//          }
//          else {
//            System.out.println("Error executing rules. Message: ");
//            System.out.println(executeResponse.getMsg());
//          }
    }

    @Test
    public void getProcessInstance() {
        ProcessServicesClient processClient = kieServicesClient.getServicesClient(ProcessServicesClient.class);

        UserTaskServicesClient userTasksClient = kieServicesClient.getServicesClient(UserTaskServicesClient.class);
        List<TaskSummary> mytaskSummaryList = userTasksClient.findTasks("krisv", 0, 20);

        for (TaskSummary taskSummary : mytaskSummaryList) {
            System.out.println("\t######### Instance taskSummary for me: " + taskSummary);
        }

        if (mytaskSummaryList.size() > 0) {
            if (!mytaskSummaryList.get(0).getStatus().equals("Completed")) {
                System.out.println("\t######### Instance taskSummary Execute: ");
                userTasksClient.startTask(containerId, mytaskSummaryList.get(0).getId(), "krisv");
                userTasksClient.completeTask(containerId, mytaskSummaryList.get(0).getId(), "krisv", null);
                System.out.println("\t######### Instance taskSummary Executeed: ");
            } else {
                Long processInstanceId = mytaskSummaryList.get(0).getProcessInstanceId();
                ProcessInstance processInstance = processClient.getProcessInstance(containerId, processInstanceId);
                System.out.println("\t######### Instance State: " + processInstance.getState());
            }
        }
    }
    @Test
    public void testDummy(){
        boolean deployContainer = true;
        KieContainerResourceList containers = kieServicesClient.listContainers().getResult();

        // check if the container is not yet deployed, if not deploy it
        if (containers != null) {
            for (KieContainerResource kieContainerResource : containers.getContainers()) {
                System.out.println("\t######### Found container " + kieContainerResource.getContainerId() + "");
                if (kieContainerResource.getContainerId().equals(containerId)) {
                    System.out.println("\t######### Found container " + containerId + " skipping deployment...");
                    deployContainer = false;
                    break;
                }
            }
        }
        
        ProcessServicesClient processClient = kieServicesClient.getServicesClient(ProcessServicesClient.class);
        
        //获取流程定义基本信息
        ProcessDefinition definition = processClient.getProcessDefinition(containerId, processId);
        System.out.println("\t######### Available ProcessDefinition:" + definition);
        //获取流程定义基本信息变量信息
        Map<String,String> variables = definition.getProcessVariables();
        System.out.println("\t######### Available ProcessDefinition Variables:" + variables);
        
        
        //获取流程定义的 用户任务
        UserTaskDefinitionList userTaskDefinitionList = processClient.getUserTaskDefinitions(containerId, processId);
        List<UserTaskDefinition> userTaskDefinitions = userTaskDefinitionList.getItems();
        for (UserTaskDefinition userTaskDefinition : userTaskDefinitions) {
            System.out.println("\t######### Definition userTaskDefinition: " + userTaskDefinition);
        }
        
        //获取流程定义 任务的输入参数
        TaskInputsDefinition taskInputDefinitions = processClient.getUserTaskInputDefinitions(containerId, processId, "直属领导审批");
        System.out.println("\t######### Definition userTaskDefinition: " + taskInputDefinitions);
        
        
        //创建流程实例
//        CorrelationKeyInfo correlationKey = new CorrelationKeyInfo();
//        CorrelationPropertyInfo info = new CorrelationPropertyInfo("b12351325ID","bvID12351235");
//        CorrelationPropertyInfo info2 = new CorrelationPropertyInfo("bID111112","bvID2321241243");
//        CorrelationPropertyInfo info3 = new CorrelationPropertyInfo("bID411111","bvID11111111");
//        correlationKey.addProperty(info);
//        correlationKey.addProperty(info2);
//        correlationKey.addProperty(info3);
//        correlationKey.setName("流程参数");
//        Long processInstanceId = processClient.startProcess(containerId, processId,correlationKey);
        
        // get details of process definition
        //查找 流程定义
        List<ProcessInstance> processInstancies = processClient.findProcessInstances(containerId, 0, 10);
        for (ProcessInstance processInstance : processInstancies) {
            System.out.println("\t######### Available processInstance:" + processInstance);
        }
        
        UserTaskServicesClient userTasksClient = kieServicesClient.getServicesClient(UserTaskServicesClient.class);
        List<TaskSummary> mytaskSummaryList = userTasksClient.findTasks("mary", 0, 20);
        
        for(TaskSummary taskSummary: mytaskSummaryList) {
        	System.out.println("\t######### ProcessInstance taskSummary: " + taskSummary);
            userTasksClient.startTask(containerId, taskSummary.getId(), taskSummary.getActualOwner());
            Map<String, Object> params =new HashMap<String, Object>();
            params.put("option", "approved");
            userTasksClient.completeTask(containerId, taskSummary.getId(), taskSummary.getActualOwner(), params);
        }
        
//        for(ProcessInstance processInstance : processInstancies) {
//        
//        	TaskSummaryList taskSummaryList = processInstance.getActiveUserTasks();
//        	if(taskSummaryList!=null) {
//	        	System.out.println("\t######### ProcessInstance taskSummaryList: " + taskSummaryList);
//	        	TaskSummary[] taskSummarys = taskSummaryList.getTasks();
//	        	System.out.println("\t######### Instance State: " + processInstance.getState());
//        	}
//        }
        
        

        
//        //获取流程实例信息
//        ProcessInstance processInstance = processClient.getProcessInstance(containerId, processInstanceId);
//        System.out.println("\t######### ProcessInstance: " + processInstance);
//        String keyId =  processInstance.getCorrelationKey();
//        System.out.println("\t######### ProcessInstance CorrelationKey: 获取流程实例信息关联信息" + keyId);
//        //获取流程实例信息关联信息
        
        
//        TaskSummaryList taskSummaryList = processInstance.getActiveUserTasks();
//        System.out.println("\t######### ProcessInstance taskSummaryList: " + taskSummaryList);
//        TaskSummary[] taskSummarys = taskSummaryList.getTasks();
//        System.out.println("\t######### Instance State: " + processInstance.getState());
//
//        UserTaskServicesClient userTasksClient = kieServicesClient.getServicesClient(UserTaskServicesClient.class);
//        
//          for (TaskSummary taskSummary : taskSummarys) {
//            System.out.println("\t######### Instance taskSummary: " + taskSummary);
//        }
//
//        List<TaskSummary> mytaskSummaryList = userTasksClient.findTasks("john", 0, 20);
//
//        for (TaskSummary taskSummary : mytaskSummaryList) {
//            System.out.println("\t######### Instance taskSummary for me: " + taskSummary);
//        }
//        
    }
}
