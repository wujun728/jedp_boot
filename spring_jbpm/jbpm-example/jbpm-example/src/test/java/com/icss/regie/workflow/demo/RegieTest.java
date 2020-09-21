package com.icss.regie.workflow.demo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.media.MediaPlayer;

import org.jbpm.persistence.correlation.CorrelationKeyInfo;
import org.jbpm.persistence.correlation.CorrelationPropertyInfo;
import org.junit.Test;
import org.kie.api.task.model.Status;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.api.model.KieContainerResource;
import org.kie.server.api.model.KieContainerResourceList;
import org.kie.server.api.model.KieServerInfo;
import org.kie.server.api.model.ReleaseId;
import org.kie.server.api.model.ServiceResponse;
import org.kie.server.api.model.admin.OrgEntities;
import org.kie.server.api.model.admin.ProcessNode;
import org.kie.server.api.model.definition.AssociatedEntitiesDefinition;
import org.kie.server.api.model.definition.ProcessDefinition;
import org.kie.server.api.model.definition.UserTaskDefinition;
import org.kie.server.api.model.definition.UserTaskDefinitionList;
import org.kie.server.api.model.instance.NodeInstance;
import org.kie.server.api.model.instance.ProcessInstance;
import org.kie.server.api.model.instance.TaskSummary;
import org.kie.server.api.model.instance.TaskSummaryList;
import org.kie.server.client.CaseServicesClient;
import org.kie.server.client.DMNServicesClient;
import org.kie.server.client.DocumentServicesClient;
import org.kie.server.client.JobServicesClient;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.kie.server.client.ProcessServicesClient;
import org.kie.server.client.QueryServicesClient;
import org.kie.server.client.RuleServicesClient;
import org.kie.server.client.SolverServicesClient;
import org.kie.server.client.UIServicesClient;
import org.kie.server.client.UserTaskServicesClient;
import org.kie.server.client.admin.CaseAdminServicesClient;
import org.kie.server.client.admin.ProcessAdminServicesClient;
import org.kie.server.client.admin.UserTaskAdminServicesClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegieTest {

    private static Logger logger = LoggerFactory.getLogger(RegieTest.class);

    private static final String URL = "http://localhost:8080/kie-server/services/rest/server";
    private static final MarshallingFormat FORMAT = MarshallingFormat.JSON;
    private static String containerId = "demo_1.0.5";
    private static String processId = "src.multiple-instance";

    @Test
    public void testGetServerInfo() {
        logger.debug("--------------获取流程服务器信息");
        String user = "zhangs";
        String pswd = "zhangs";
        KieServicesConfiguration conf = KieServicesFactory.newRestConfiguration(URL, user, pswd);
        conf.setMarshallingFormat(FORMAT);
        KieServicesClient kieServicesClient = KieServicesFactory.newKieServicesClient(conf);
        ServiceResponse<KieServerInfo> KieServerInfo = kieServicesClient.getServerInfo();
        logger.debug(KieServerInfo.toString());
    }

    @Test
    public void testCreateContainer() {
        logger.debug("--------------部署流程容器");
        boolean deployContainer = true;
        String user = "zhangs";
        String pswd = "zhangs";
        KieServicesConfiguration conf = KieServicesFactory.newRestConfiguration(URL, user, pswd);
        conf.setMarshallingFormat(FORMAT);
        KieServicesClient kieServicesClient = KieServicesFactory.newKieServicesClient(conf);
        
        KieContainerResourceList containers = kieServicesClient.listContainers().getResult();
        // check if the container is not yet deployed, if not deploy it
        if (containers != null) {
            for (KieContainerResource kieContainerResource : containers.getContainers()) {
                logger.debug("\t######### Found container " + kieContainerResource.getContainerId() + "");
                if (kieContainerResource.getContainerId().equals(containerId)) {
                    logger.debug("\t######### Found container " + containerId + " skipping deployment...");
                    deployContainer = false;
                    break;
                }
            }
        }

        // deploy container if not there yet        
        if (deployContainer) {
            logger.debug("\t######### Deploying container " + containerId);
            KieContainerResource resource = new KieContainerResource(containerId, new ReleaseId("com.icss.regie.workflow.demo", "regie-process", "1.0.4"));
            kieServicesClient.createContainer(containerId, resource);
        }
    }

    @Test
    public void testListProcessDefinition() {
        logger.debug("--------------获取流程定义列表");
        String user = "zhangs";
        String pswd = "zhangs";
        KieServicesConfiguration conf = KieServicesFactory.newRestConfiguration(URL, user, pswd);
        conf.setMarshallingFormat(FORMAT);
        KieServicesClient kieServicesClient = KieServicesFactory.newKieServicesClient(conf);
        QueryServicesClient queryClient = kieServicesClient.getServicesClient(QueryServicesClient.class);
        List<ProcessDefinition> processes = queryClient.findProcesses(0, 10);
        
        ProcessServicesClient processServicesClient = kieServicesClient.getServicesClient(ProcessServicesClient.class);
        UserTaskDefinitionList userTaskDefinitionList = processServicesClient.getUserTaskDefinitions(containerId, processId);
        logger.debug(" userTaskDefinitionList："+ userTaskDefinitionList);
        if(userTaskDefinitionList!=null){
            for(UserTaskDefinition userTask: userTaskDefinitionList.getItems()){
                logger.debug(" userTask："+ userTask);
                
            }
        }
        AssociatedEntitiesDefinition associatedEntitiesDefinition = processServicesClient.getAssociatedEntityDefinitions(containerId, processId);
        logger.debug(" associatedEntitiesDefinition："+ associatedEntitiesDefinition);
        if(associatedEntitiesDefinition!=null){
            associatedEntitiesDefinition.getAssociatedEntities().forEach((k,v)->{
                logger.debug("Key : " + k + ", Value : " + Arrays.toString(v));
            });
        }

        ProcessAdminServicesClient processAdminServicesClient = kieServicesClient.getServicesClient(ProcessAdminServicesClient.class);
        CaseServicesClient caseServicesClient = kieServicesClient.getServicesClient(CaseServicesClient.class);
        CaseAdminServicesClient caseAdminServicesClient = kieServicesClient.getServicesClient(CaseAdminServicesClient.class);
        UserTaskServicesClient userTaskServicesClient = kieServicesClient.getServicesClient(UserTaskServicesClient.class);
        UserTaskAdminServicesClient userTaskAdminServicesClient = kieServicesClient.getServicesClient(UserTaskAdminServicesClient.class);
        DMNServicesClient dmnServicesClient = kieServicesClient.getServicesClient(DMNServicesClient.class);
        DocumentServicesClient documentServicesClient = kieServicesClient.getServicesClient(DocumentServicesClient.class);
        JobServicesClient jobServicesClient = kieServicesClient.getServicesClient(JobServicesClient.class);
        QueryServicesClient queryServicesClient = kieServicesClient.getServicesClient(QueryServicesClient.class);
        RuleServicesClient ruleServicesClient = kieServicesClient.getServicesClient(RuleServicesClient.class);
        SolverServicesClient solverServicesClient = kieServicesClient.getServicesClient(SolverServicesClient.class);
        UIServicesClient uiServicesClient = kieServicesClient.getServicesClient(UIServicesClient.class);
        
        
        
    }

    @Test
    public void testListProcessInstance() {
        logger.debug("--------------获取流程实例列表");
        String user = "zhangs";
        String pswd = "zhangs";
        KieServicesConfiguration conf = KieServicesFactory.newRestConfiguration(URL, user, pswd);
        conf.setMarshallingFormat(FORMAT);
        KieServicesClient kieServicesClient = KieServicesFactory.newKieServicesClient(conf);
        QueryServicesClient queryClient = kieServicesClient.getServicesClient(QueryServicesClient.class);
        List<Integer> status = Arrays.asList(new Integer[]{0,1});
        List<ProcessInstance> processInstance = queryClient.findProcessInstancesByStatus(status,0, 10);
        logger.debug("\t######### Available ProcessInstance:" + processInstance);
        for (ProcessInstance process : processInstance) {
            TaskSummaryList taskSummarys = process.getActiveUserTasks();
            if (taskSummarys != null) {

                for (TaskSummary tasksummary : taskSummarys.getTasks()) {
                    logger.debug("\t######### Available TaskSummary:" + tasksummary);
                }
            }

            logger.debug("\t######### Available ProcessInstance:" + process);
        }
    }

    @Test
    public void testAbortProcessInstance() {
        logger.debug("--------------获取流程实例列表");
        String user = "zhangs";
        String pswd = "zhangs";

    }
    @Test
    public void testProcessDefinationUserTask() {
        logger.debug("--------------获取流程用户任务");
        boolean deployContainer = true;
        String user = "zhangs";
        String pswd = "zhangs";
        KieServicesConfiguration conf = KieServicesFactory.newRestConfiguration(URL, user, pswd);
        conf.setMarshallingFormat(FORMAT);
        KieServicesClient kieServicesClient = KieServicesFactory.newKieServicesClient(conf);
        ProcessServicesClient processServicesClient = kieServicesClient.getServicesClient(ProcessServicesClient.class);
        ProcessAdminServicesClient processAdminServicesClient = kieServicesClient.getServicesClient(ProcessAdminServicesClient.class);
        CaseServicesClient caseServicesClient = kieServicesClient.getServicesClient(CaseServicesClient.class);
        CaseAdminServicesClient caseAdminServicesClient = kieServicesClient.getServicesClient(CaseAdminServicesClient.class);
        UserTaskServicesClient userTaskServicesClient = kieServicesClient.getServicesClient(UserTaskServicesClient.class);
        UserTaskAdminServicesClient userTaskAdminServicesClient = kieServicesClient.getServicesClient(UserTaskAdminServicesClient.class);
        DMNServicesClient dmnServicesClient = kieServicesClient.getServicesClient(DMNServicesClient.class);
        DocumentServicesClient documentServicesClient = kieServicesClient.getServicesClient(DocumentServicesClient.class);
        JobServicesClient jobServicesClient = kieServicesClient.getServicesClient(JobServicesClient.class);
        QueryServicesClient queryServicesClient = kieServicesClient.getServicesClient(QueryServicesClient.class);
        RuleServicesClient ruleServicesClient = kieServicesClient.getServicesClient(RuleServicesClient.class);
        SolverServicesClient solverServicesClient = kieServicesClient.getServicesClient(SolverServicesClient.class);
        UIServicesClient uiServicesClient = kieServicesClient.getServicesClient(UIServicesClient.class);

    }
    @Test
    public void testDisposeContainer() {
        logger.debug("--------------停用流程容器");
        boolean deployContainer = true;
        String user = "zhangs";
        String pswd = "zhangs";
        KieServicesConfiguration conf = KieServicesFactory.newRestConfiguration(URL, user, pswd);
        conf.setMarshallingFormat(FORMAT);
        KieServicesClient kieServicesClient = KieServicesFactory.newKieServicesClient(conf);
        kieServicesClient.disposeContainer(containerId);
    }

    @Test
    public void testUpdateContainer() {
        logger.debug("--------------更新流程容器");
        boolean deployContainer = true;
        String user = "zhangs";
        String pswd = "zhangs";
        KieServicesConfiguration conf = KieServicesFactory.newRestConfiguration(URL, user, pswd);
        conf.setMarshallingFormat(FORMAT);
        KieServicesClient kieServicesClient = KieServicesFactory.newKieServicesClient(conf);

        KieContainerResourceList containers = kieServicesClient.listContainers().getResult();
        // check if the container is not yet deployed, if not deploy it
        if (containers != null) {
            for (KieContainerResource kieContainerResource : containers.getContainers()) {
                logger.debug("\t######### Found container " + kieContainerResource.getContainerId() + "");
                if (kieContainerResource.getContainerId().equals(containerId)) {
                    logger.debug("\t######### Found container " + containerId + " skipping deployment...");
                    deployContainer = false;
                    break;
                }
            }
        }

        // deploy container if not there yet        
        if (deployContainer) {
            logger.debug("\t######### Deploying container " + containerId);
            ReleaseId releaseId = new ReleaseId("com.icss.regie.workflow.demo", "regie-process", "1.0.1");
            kieServicesClient.updateReleaseId(containerId, releaseId);
        }
    }

    @Test
    public void testStartProcess() {
        logger.debug("--------------开启一个流程实例");
        String user = "zhangs";
        String pswd = "zhangs";
        KieServicesConfiguration conf = KieServicesFactory.newRestConfiguration(URL, user, pswd);
        conf.setMarshallingFormat(FORMAT);
        KieServicesClient kieServicesClient = KieServicesFactory.newKieServicesClient(conf);
        ProcessServicesClient processClient = kieServicesClient.getServicesClient(ProcessServicesClient.class);
        ProcessAdminServicesClient processAdminServicesClient = kieServicesClient.getServicesClient(ProcessAdminServicesClient.class);
        CorrelationKeyInfo correlationKey = new CorrelationKeyInfo();
        CorrelationPropertyInfo info = new CorrelationPropertyInfo("1", "110");
        correlationKey.addProperty(info);
//        correlationKey.addProperty(info2);
//        correlationKey.addProperty(info3);
        correlationKey.setName("params");
        Map<String, Object> variables = new HashMap<String, Object>();
        
        variables.put("assigneeList", Arrays.asList(new String[]{"zhangs","hanl","wangjl","leiy"}));
        variables.put("assignee", "zhangs");
        Long processInstanceId = processClient.startProcess(containerId, processId, correlationKey,variables);
        logger.debug("开启一个流程实例：流程实例ID:" + processInstanceId);
        ProcessInstance processInstance = processClient.getProcessInstance(containerId, processInstanceId);
        QueryServicesClient queryClient = kieServicesClient.getServicesClient(QueryServicesClient.class);

        List<NodeInstance> nodeInstances = queryClient.findActiveNodeInstances(processInstance.getId(), 0, 10);
        System.out.println("List<NodeInstance>:" + nodeInstances);
        for(NodeInstance ins: nodeInstances) {
        	 System.out.println("NodeInstance:" + ins);
        	 if(ins.getNodeType().equals("HumanTaskNode")) {
        		 
        	 }
        }
        
        TaskSummaryList taskSummaryList = processInstance.getActiveUserTasks();
        if(taskSummaryList!=null) {
	        TaskSummary[] taskSummarys = taskSummaryList.getTasks();
	        logger.debug("\t######### Instance State: " + processInstance.getState());
	        UserTaskServicesClient userTasksClient = kieServicesClient.getServicesClient(UserTaskServicesClient.class);
	        //processClient.abortProcessInstance(containerId, processInstanceId);
	        for (TaskSummary taskSummary : taskSummarys) {
	            logger.debug("\t######### Instance taskSummary: " + taskSummary);
	        }
        }
    }

    
    @Test
    public void testLeaderApprove() {
        logger.debug("--------------直属领导审批");
        String user = "hanl";
        String pswd = "hanl";
        List<String> groups = Arrays.asList(new String[]{"TL"});
        List<String> status = Arrays.asList(new String[]{Status.Reserved.name(),Status.Completed.name()});
        KieServicesConfiguration conf = KieServicesFactory.newRestConfiguration(URL, user, pswd);
        conf.setMarshallingFormat(FORMAT);
        KieServicesClient kieServicesClient = KieServicesFactory.newKieServicesClient(conf);
        UserTaskServicesClient userTasksClient = kieServicesClient.getServicesClient(UserTaskServicesClient.class);
        List<TaskSummary> mytaskSummaryList = userTasksClient.findTasksAssignedAsPotentialOwner(user, 0, 20);
        userTasksClient.startTask(containerId, 15l, user);
        userTasksClient.completeTask(containerId, 15l, user,null);
//        for (TaskSummary taskSummary : mytaskSummaryList) {
//            logger.debug("\t######### Instance taskSummary for me: " + taskSummary);
//            userTasksClient.startTask(containerId, taskSummary.getId(), user);
//            try{
//            Map<String, Object> variables = new HashMap<String, Object>();
//            //variables.put("currGroupId", "PM");
//            userTasksClient.completeTask(containerId, taskSummary.getId(), user,null);
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//            logger.debug("\t######### Instance taskSummary Executeed: ");
//        }
    }

    @Test
    public void testManagerApprove() {
        logger.debug("--------------管理领导审批");
        String user = "zhufk";
        String pswd = "zhufk";
        KieServicesConfiguration conf = KieServicesFactory.newRestConfiguration(URL, user, pswd);
        conf.setMarshallingFormat(FORMAT);
        KieServicesClient kieServicesClient = KieServicesFactory.newKieServicesClient(conf);
        UserTaskServicesClient userTasksClient = kieServicesClient.getServicesClient(UserTaskServicesClient.class);
        List<TaskSummary> mytaskSummaryList = userTasksClient.findTasksAssignedAsPotentialOwner(user, 0, 20);
        for (TaskSummary taskSummary : mytaskSummaryList) {
            logger.debug("\t######### Instance taskSummary for me: " + taskSummary);
            //userTasksClient.startTask(containerId, taskSummary.getId(), user);
            //userTasksClient.completeTask(containerId, taskSummary.getId(), user, null);
            logger.debug("\t######### Instance taskSummary Executeed: ");
        }
    }
    
    @Test
    public void testModifyGroupAndActor(){
        logger.debug("--------------重新分配流程actor");
        boolean deployContainer = true;
        String user = "zhangs";
        String pswd = "zhangs";
        KieServicesConfiguration conf = KieServicesFactory.newRestConfiguration(URL, user, pswd);
        conf.setMarshallingFormat(FORMAT);
        KieServicesClient kieServicesClient = KieServicesFactory.newKieServicesClient(conf);
        ProcessAdminServicesClient processAdminServicesClient = kieServicesClient.getServicesClient(ProcessAdminServicesClient.class);
         ProcessServicesClient processServicesClient = kieServicesClient.getServicesClient(ProcessServicesClient.class);
        List<ProcessNode> processNodes = processAdminServicesClient.getProcessNodes(containerId, 16l);
        logger.debug("\t######### List ProcessNode: "+ processNodes);
        List<NodeInstance> nodeInstances = processAdminServicesClient.getActiveNodeInstances(containerId, 16l);
        logger.debug("\t######### List NodeInstance: "+ nodeInstances);
        UserTaskAdminServicesClient userTaskAdminServicesClient = kieServicesClient.getServicesClient(UserTaskAdminServicesClient.class);
        UserTaskServicesClient userTaskServicesClient = kieServicesClient.getServicesClient(UserTaskServicesClient.class);
        List<TaskSummary> mytaskSummaryList = userTaskServicesClient.findTasksAssignedAsPotentialOwner(user, 0, 20);
        if(mytaskSummaryList!=null){
            mytaskSummaryList.stream().forEach(task -> {
                logger.debug("\t######### task: "+ task);
            });
        }
        //userTaskServicesClient.updateTask(containerId, Long.MAX_VALUE, user, updatedTask);
//        userTaskAdminServicesClient.removePotentialOwnerGroups(containerId, 14l, "TL");
//        OrgEntities orgEntities = new OrgEntities();
//        orgEntities.setUsers(Arrays.asList(new String[]{"zhangs"}));
//        userTaskAdminServicesClient.addPotentialOwners(containerId, 14l, true, orgEntities);
    }
    
    @Test
    public void testMultipleInstance(){
        logger.debug("--------------多实例测试");
        KieServicesConfiguration hanl = KieServicesFactory.newRestConfiguration(URL, "hanl", "hanl");
        hanl.setMarshallingFormat(FORMAT);
        KieServicesClient hanlClient = KieServicesFactory.newKieServicesClient(hanl);
        
        UserTaskServicesClient hanlUserTasksClient = hanlClient.getServicesClient(UserTaskServicesClient.class);
        
        KieServicesConfiguration zhangs = KieServicesFactory.newRestConfiguration(URL, "zhangs", "zhangs");
        hanl.setMarshallingFormat(FORMAT);
        KieServicesClient zhangsClient = KieServicesFactory.newKieServicesClient(zhangs);
        
        UserTaskServicesClient zhangsUserTasksClient = zhangsClient.getServicesClient(UserTaskServicesClient.class);
        
        KieServicesConfiguration wangjl = KieServicesFactory.newRestConfiguration(URL, "wangjl", "wangjl");
        hanl.setMarshallingFormat(FORMAT);
        KieServicesClient wangjlClient = KieServicesFactory.newKieServicesClient(wangjl);
        UserTaskServicesClient wangjlUserTasksClient = wangjlClient.getServicesClient(UserTaskServicesClient.class);
        
        KieServicesConfiguration leiy = KieServicesFactory.newRestConfiguration(URL, "leiy", "leiy");
        leiy.setMarshallingFormat(FORMAT);
        KieServicesClient leiyClient = KieServicesFactory.newKieServicesClient(leiy);
        UserTaskServicesClient leiyUserTasksClient = leiyClient.getServicesClient(UserTaskServicesClient.class);
        
        Map<String,Object> results = new HashMap<>();
        
        results.put("decision", true);
        
        zhangsUserTasksClient.startTask(containerId, 31l, "zhangs");
        zhangsUserTasksClient.completeTask(containerId, 31l, "zhangs",results);
        
        hanlUserTasksClient.startTask(containerId, 32l, "hanl");
        hanlUserTasksClient.completeTask(containerId, 32l, "hanl",results);
        
        wangjlUserTasksClient.startTask(containerId, 33l, "wangjl");
        wangjlUserTasksClient.completeTask(containerId, 33l, "wangjl",results);
        
        leiyUserTasksClient.startTask(containerId, 34l, "leiy");
        leiyUserTasksClient.completeTask(containerId, 34l, "leiy",results);
    }
    
    @Test
    public void testListFilter(){
        List<Boolean> results = Arrays.asList(new Boolean[]{true,true,true,false});
        long i = results.stream().filter((v)->{return v.equals(true);}).count();
        int c = results.stream().map((v) ->{return v?1:0;}).reduce(0, (sum, item) -> {return sum + item;});
        System.out.println(i);
        System.out.println(c);
        Boolean b = results.stream().filter(item->item).count()>1;
    }
}
