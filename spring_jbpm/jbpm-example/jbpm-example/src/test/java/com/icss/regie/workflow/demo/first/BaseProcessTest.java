package com.icss.regie.workflow.demo.first;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.bpmn2.core.Message;
import org.kie.api.runtime.Context;
import org.jbpm.process.workitem.rest.RESTWorkItemHandler;
import org.jbpm.services.task.commands.TaskContext;
import org.jbpm.services.task.impl.model.UserImpl;
import org.jbpm.test.JbpmJUnitBaseTestCase;
import org.junit.Assert;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.TaskService;
import org.kie.api.task.model.OrganizationalEntity;
import org.kie.api.task.model.Task;
import org.kie.api.task.model.TaskSummary;
import org.kie.internal.runtime.manager.context.ProcessInstanceIdContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseProcessTest extends JbpmJUnitBaseTestCase {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseProcessTest.class);
	
	
//	@Before
//    public void setUp() throws Exception {
//		super.setUp();
//		SimpleService service = new SimpleService();
//        Endpoint.publish("http://127.0.0.1:9876/HelloService", service);
//	}
//	
	public BaseProcessTest() {
		super(true,true);
	}
	@Test
	public void testFirstProcess() {
		logger.debug("==========testFirstProcess============");
		RuntimeManager manager = createRuntimeManager("com/icss/regie/workflow/demo/first/first.bpmn2");
		RuntimeEngine engine = manager.getRuntimeEngine(null);
		KieSession ksession = engine.getKieSession();
		TaskService taskService = engine.getTaskService();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("recipient", "test");
		ksession.startProcess("com.icss.regie.workflow.demo.first", params);
		manager.disposeRuntimeEngine(engine);
	}
	@Test
	public void testHelloProcess() {
		logger.debug("==========testHelloProcess============");
		RuntimeManager manager = createRuntimeManager("com/icss/regie/workflow/demo/hello/hello.bpmn2");
		RuntimeEngine engine = manager.getRuntimeEngine(null);
		KieSession ksession = engine.getKieSession();
		TaskService taskService = engine.getTaskService();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("recipient", "test");
		ksession.startProcess("com.icss.regie.workflow.demo.hello", params);

		List<TaskSummary> list = taskService.getTasksOwned("john", "en-UK");
		TaskSummary task = list.get(0);
		taskService.start(task.getId(), "john");
		taskService.complete(task.getId(), "john", null);

		list = taskService.getTasksOwned("mary", "en-UK");
		task = list.get(0);
		taskService.start(task.getId(), "mary");
		taskService.complete(task.getId(), "mary", null);

		manager.disposeRuntimeEngine(engine);
	}
	
	@Test
	public void testXORProcess() {
		logger.debug("==========testXORProcess============");
		RuntimeManager manager = createRuntimeManager("com/icss/regie/workflow/demo/xor/xor.bpmn2");
		RuntimeEngine engine = manager.getRuntimeEngine(null);
		KieSession ksession = engine.getKieSession();
		TaskService taskService = engine.getTaskService();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("recipient", "test");
        params.put("dept", "a");
		ksession.startProcess("com.icss.regie.workflow.demo.xor", params);

        TaskSummary task;
        List<TaskSummary> list ;
        
        list = taskService.getTasksOwned("krisv", "en-UK");
        if (list.size() > 0) {
            System.out.println("mingshu has task");
            task = list.get(0);
            taskService.start(task.getId(), "krisv");
            taskService.complete(task.getId(), "krisv", null);
        }
        list = taskService.getTasksOwned("salaboy", "en-UK");
        if (list.size() > 0) {
            System.out.println("xiaoming has task");
            task = list.get(0);
            taskService.start(task.getId(), "salaboy");
            taskService.complete(task.getId(), "salaboy", null);
        }

		manager.disposeRuntimeEngine(engine);
	}
	@Test
	public void testRSProcess() {
		logger.debug("==========testRSProcess============");
		RuntimeManager manager = createRuntimeManager("com/icss/regie/workflow/demo/rs/rs.bpmn2");
		RuntimeEngine engine = manager.getRuntimeEngine(null);
		KieSession ksession = engine.getKieSession();
		ksession.getWorkItemManager().registerWorkItemHandler("Rest", new RESTWorkItemHandler());
		TaskService taskService = engine.getTaskService();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("recipient", "test");
        params.put("dept", "a");
		ksession.startProcess("com.icss.regie.workflow.demo.rs", params);
		manager.disposeRuntimeEngine(engine);
	}
	@Test
	public void testWSProcess() {
		logger.debug("==========testWSProcess============");
		RuntimeManager manager = createRuntimeManager("com/icss/regie/workflow/demo/ws/ws.bpmn2");
		RuntimeEngine engine = manager.getRuntimeEngine(null);
		KieSession ksession = engine.getKieSession();
		//ksession.getWorkItemManager().registerWorkItemHandler("WebService");
		TaskService taskService = engine.getTaskService();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("recipient", "test");
        params.put("dept", "a");
        params.put("requestBody", "上海");
		ksession.startProcess("com.icss.regie.workflow.demo.ws", params);
		manager.disposeRuntimeEngine(engine);
	}
	@Test
	public void testEmbedProcess() {
		logger.debug("==========testWSProcess============");
		RuntimeManager manager = createRuntimeManager("com/icss/regie/workflow/demo/main/main.bpmn2");
		RuntimeEngine engine = manager.getRuntimeEngine(null);
		KieSession ksession = engine.getKieSession();
		ksession.startProcess("com.icss.regie.workflow.demo.main");
		manager.disposeRuntimeEngine(engine);
	}
	@Test
	public void testReusableProcess() {
		logger.debug("==========testReusableProcess============");
		RuntimeManager manager = createRuntimeManager("com/icss/regie/workflow/demo/main2/main2.bpmn2","com/icss/regie/workflow/demo/sub/sub.bpmn2");
		RuntimeEngine engine = manager.getRuntimeEngine(null);
		List<String> messages = new ArrayList<String>();  
        messages.add("message 1");  
        messages.add("message 2");
        messages.add("message 3");
        Map<String,Object> parameters = new HashMap<String, Object>();  
        parameters.put("messages", messages);
		KieSession ksession = engine.getKieSession();
		ProcessInstance process = ksession.startProcess("com.icss.regie.workflow.demo.main2",parameters);
		manager.disposeRuntimeEngine(engine);
	}
	@Test
	public void testSimpleTimerProcess() {
		logger.debug("==========testSimpleTimerProcess============");
		RuntimeManager manager = createRuntimeManager("com/icss/regie/workflow/demo/simpletimer/simpletimer.bpmn2");
		RuntimeEngine engine = manager.getRuntimeEngine(null);
		KieSession ksession = engine.getKieSession();
		ProcessInstance process = ksession.startProcess("com.icss.regie.workflow.demo.simpletimer");
		manager.disposeRuntimeEngine(engine);
	}
	@Test
	public void testTimerCycleProcess() {
		logger.debug("==========testTimerCycleProcess============");
		RuntimeManager manager = createRuntimeManager("com/icss/regie/workflow/demo/timer-cycle/timercycle.bpmn2");
		RuntimeEngine engine = manager.getRuntimeEngine(null);
		KieSession ksession = engine.getKieSession();
		ProcessInstance process = ksession.startProcess("com.icss.regie.workflow.demo.timercycle");
		manager.disposeRuntimeEngine(engine);
	}
	@Test
	public void testTimerEventProcess() throws InterruptedException {
		logger.debug("==========testTimerEventProcess============");
		RuntimeManager manager = createRuntimeManager("com/icss/regie/workflow/demo/event/timerevent.bpmn2");
		RuntimeEngine engine = manager.getRuntimeEngine(null);
		KieSession ksession = engine.getKieSession();
		ProcessInstance process = ksession.startProcess("com.icss.regie.workflow.demo.timerevent");
		Thread.sleep(10000);
		manager.disposeRuntimeEngine(engine);
	}
	@Test
	public void testTimerEvent2Process() throws InterruptedException {
		logger.debug("==========testTimerEventProcess============");
		RuntimeManager manager = createRuntimeManager("com/icss/regie/workflow/demo/event/timerevent.bpmn2");
		RuntimeEngine engine = manager.getRuntimeEngine(null);
		KieSession ksession = engine.getKieSession();
		ProcessInstance process = ksession.startProcess("com.icss.regie.workflow.demo.timerevent");
		Thread.sleep(5000);
        TaskSummary task;
        List<TaskSummary> list ;
		TaskService taskService = engine.getTaskService();
        list = taskService.getTasksOwned("john", "en-UK");
        if (list.size() > 0) {
            System.out.println("john has task");
            task = list.get(0);
            taskService.start(task.getId(), "john");
            taskService.complete(task.getId(), "john", null);
        }
        Thread.sleep(3000);
		manager.disposeRuntimeEngine(engine);
	}
	@Test
	public void testTimerEvent3Process() throws InterruptedException {
		logger.debug("==========testTimerEventProcess============");
		RuntimeManager manager = createRuntimeManager("com/icss/regie/workflow/demo/event/timerevent.bpmn2");
		RuntimeEngine engine = manager.getRuntimeEngine(null);
		KieSession ksession = engine.getKieSession();
		ProcessInstance process = ksession.startProcess("com.icss.regie.workflow.demo.timerevent");
		Thread.sleep(5000);
        TaskSummary task;
        List<TaskSummary> list ;
		TaskService taskService = engine.getTaskService();
        list = taskService.getTasksOwned("john", "en-UK");
        if (list.size() > 0) {
            System.out.println("john has task");
            task = list.get(0);
            taskService.start(task.getId(), "john");
            taskService.complete(task.getId(), "john", null);
        }
        list = taskService.getTasksOwned("mary", "en-UK");
        if (list.size() > 0) {
            System.out.println("mary has task");
            task = list.get(0);
            taskService.start(task.getId(), "mary");
            taskService.complete(task.getId(), "mary", null);
        }
		manager.disposeRuntimeEngine(engine);
	}
	@Test
	public void testTimerProcess() {
		logger.debug("==========testTimerProcess============");
		RuntimeManager manager = createRuntimeManager("com/icss/regie/workflow/demo/timer/timer.bpmn2");
		RuntimeEngine engine = manager.getRuntimeEngine(null);
		KieSession ksession = engine.getKieSession();
		ProcessInstance process = ksession.startProcess("com.icss.regie.workflow.demo.timer");
		manager.disposeRuntimeEngine(engine);
	}
	@Test
	public void testTimerHomanProcess() {
		logger.debug("==========testTimerProcess============");
		RuntimeManager manager = createRuntimeManager("com/icss/regie/workflow/demo/timer/timer.bpmn2");
		RuntimeEngine engine = manager.getRuntimeEngine(null);
		KieSession ksession = engine.getKieSession();
		ProcessInstance process = ksession.startProcess("com.icss.regie.workflow.demo.timer");
		
        TaskSummary task;
        List<TaskSummary> list ;
        TaskService taskService = engine.getTaskService();
        
        list = taskService.getTasksOwned("john", "en-UK");
        if (list.size() > 0) {
            System.out.println("john has task");
            task = list.get(0);
            taskService.start(task.getId(), "john");
            taskService.complete(task.getId(), "john", null);
        }
        
		manager.disposeRuntimeEngine(engine);
	}
	
	@Test
	public void testSignalThrowYESProcess() {
		logger.debug("==========testSignalThrowProcess============");
		RuntimeManager manager = createRuntimeManager("com/icss/regie/workflow/demo/signal/signalthrow.bpmn2");
		RuntimeEngine engine = manager.getRuntimeEngine(null);
		KieSession ksession = engine.getKieSession();
        Map<String,Object> parameters = new HashMap<String, Object>();  
        parameters.put("flag", "YES");
		ProcessInstance process = ksession.startProcess("com.icss.regie.workflow.demo.signalthrow",parameters);
		Message message = new Message("Message_input");
		
//        TaskSummary task;
//        List<TaskSummary> list ;
//        TaskService taskService = engine.getTaskService();
//        
//        list = taskService.getTasksOwned("john", "en-UK");
//        if (list.size() > 0) {
//            System.out.println("john has task");
//            task = list.get(0);
//            taskService.start(task.getId(), "john");
//            taskService.complete(task.getId(), "john", null);
//        }
        
		manager.disposeRuntimeEngine(engine);
	}
	@Test
	public void testSignalThrowNOProcess() {
		logger.debug("==========testSignalThrowProcess============");
		RuntimeManager manager = createRuntimeManager("com/icss/regie/workflow/demo/signal/signalthrow.bpmn2");
		RuntimeEngine engine = manager.getRuntimeEngine(null);
		KieSession ksession = engine.getKieSession();
        Map<String,Object> parameters = new HashMap<String, Object>();  
        parameters.put("flag", "NO");
		//ProcessInstance process = ksession.startProcess("com.icss.regie.workflow.demo.signalthrow",parameters);
		//Assert.assertTrue(process.getState() == ProcessInstance.STATE_ACTIVE);
		Message message = new Message("Message_input");
		ksession.insert(message);
		int tot = ksession.fireAllRules();
		System.out.println("============"+tot);
//        TaskSummary task;
//        List<TaskSummary> list ;
//        TaskService taskService = engine.getTaskService();
//        
//        list = taskService.getTasksOwned("john", "en-UK");
//        if (list.size() > 0) {
//            System.out.println("john has task");
//            task = list.get(0);
//            taskService.start(task.getId(), "john");
//            taskService.complete(task.getId(), "john", null);
//        }
        
		manager.disposeRuntimeEngine(engine);
	}
	@Test
	public void testSignalCatchProcess() {
		logger.debug("==========testSignalCatchProcess============");
		RuntimeManager manager = createRuntimeManager("com/icss/regie/workflow/demo/signal/signalcatch.bpmn2");
		RuntimeEngine engine = manager.getRuntimeEngine(null);
		KieSession ksession = engine.getKieSession();
		ProcessInstance process = ksession.startProcess("com.icss.regie.workflow.demo.signalcatch");
		ksession.signalEvent("Signal_first", "100");
        TaskSummary task;
        List<TaskSummary> list ;
        TaskService taskService = engine.getTaskService();
        
        list = taskService.getTasksOwned("john", "en-UK");
        if (list.size() > 0) {
            System.out.println("john has task");
            task = list.get(0);
            taskService.start(task.getId(), "john");
            taskService.complete(task.getId(), "john", null);
        }
		manager.disposeRuntimeEngine(engine);
	}
	@Test
	public void testSignalCatchNOProcess() {
		logger.debug("==========testSignalCatchProcess============");
		RuntimeManager manager = createRuntimeManager("com/icss/regie/workflow/demo/signal/signalcatch.bpmn2");
		RuntimeEngine engine = manager.getRuntimeEngine(null);
		KieSession ksession = engine.getKieSession();
		ProcessInstance process = ksession.startProcess("com.icss.regie.workflow.demo.signalcatch");
		//ksession.signalEvent("Signal_first", "100");
        TaskSummary task;
        List<TaskSummary> list ;
        TaskService taskService = engine.getTaskService();
        
        list = taskService.getTasksOwned("john", "en-UK");
        if (list.size() > 0) {
            System.out.println("john has task");
            task = list.get(0);
            taskService.start(task.getId(), "john");
            taskService.complete(task.getId(), "john", null);
        }
		manager.disposeRuntimeEngine(engine);
	}
	
	@Test
	public void testSignalProcess() {
		logger.debug("==========testSignalCatchProcess============");
		RuntimeManager manager = createRuntimeManager("com/icss/regie/workflow/demo/signal/signalcatch.bpmn2","com/icss/regie/workflow/demo/signal/signalthrow.bpmn2");
		RuntimeEngine engine = manager.getRuntimeEngine(null);
		KieSession ksession = engine.getKieSession();
        Map<String,Object> parameters = new HashMap<String, Object>();  
        parameters.put("flag", "YES");
		ProcessInstance catchinstance = ksession.startProcess("com.icss.regie.workflow.demo.signalcatch");
		ProcessInstance throwinstance = ksession.startProcess("com.icss.regie.workflow.demo.signalthrow",parameters);
        TaskSummary task;
        List<TaskSummary> list ;
        TaskService taskService = engine.getTaskService();
        
        list = taskService.getTasksOwned("john", "en-UK");
        if (list.size() > 0) {
            System.out.println("john has task");
            task = list.get(0);
            taskService.start(task.getId(), "john");
            taskService.complete(task.getId(), "john", null);
        }
		manager.disposeRuntimeEngine(engine);
	}
	
	@Test
	public void testGroupProcess() {
		logger.debug("==========testGroupProcess============");
		RuntimeManager manager = createRuntimeManager("com/icss/regie/workflow/demo/group/group.bpmn2");
		RuntimeEngine engine = manager.getRuntimeEngine(null);
		KieSession ksession = engine.getKieSession();
        Map<String,Object> parameters = new HashMap<String, Object>();  
        parameters.put("flag", "YES");
		ProcessInstance group = ksession.startProcess("com.icss.regie.workflow.demo.group");
        TaskSummary task;
        List<TaskSummary> list ;
        TaskService taskService = engine.getTaskService();
        
        System.out.println("ProcessInstance : "+ group);
        //List status = Arrays.asList(new Status[] { Status.Ready });
        list = taskService.getTasksAssignedAsPotentialOwner("mary", "en-UK");
        System.out.println("mary has task : "+ list.size());
        
        list = taskService.getTasksAssignedAsPotentialOwner("manager", "en-UK");
        System.out.println("manager has task : "+ list.size());
//        if (list.size() > 0) {
//            System.out.println("john has task");
//            task = list.get(0);
//            taskService.start(task.getId(), "john");
//            taskService.complete(task.getId(), "john", null);
//        }
		manager.disposeRuntimeEngine(engine);
	}
	
	@Test
	public void testGroupActorProcess() {
		logger.debug("==========testGroupProcess============");
		RuntimeManager manager = createRuntimeManager("com/icss/regie/workflow/demo/group/group-actor.bpmn2");
		RuntimeEngine engine = manager.getRuntimeEngine(null);
		KieSession ksession = engine.getKieSession();
        Map<String,Object> parameters = new HashMap<String, Object>();  
        parameters.put("flag", "YES");
		ProcessInstance group = ksession.startProcess("com.icss.regie.workflow.demo.group.group-actor");
        TaskSummary task;
        List<TaskSummary> list;
        TaskService taskService = engine.getTaskService();
        System.out.println("ProcessInstance : "+ group);
        //List status = Arrays.asList(new Status[] { Status.Ready });
        list = taskService.getTasksAssignedAsPotentialOwner("mary", "en-UK");

        System.out.println("mary has task : "+ list.size());
        
        list = taskService.getTasksAssignedAsPotentialOwner("manager", "en-UK");
        System.out.println("manager has task : "+ list.size());
        
        list = taskService.getTasksAssignedAsPotentialOwner("john", "en-UK");
        System.out.println("john has task : "+ list.size());
//        if (list.size() > 0) {
//            System.out.println("john has task");
//            task = list.get(0);
//            taskService.start(task.getId(), "john");
//            taskService.complete(task.getId(), "john", null);
//        }
        
        
        list.stream().forEach( t -> {
        	 System.out.println("TaskSummary : "+ t.getId());
        	 Context context = (Context)ProcessInstanceIdContext.get(t.getProcessInstanceId());
        	 TaskContext tContext = (TaskContext)context;
        	 Task tt = tContext.getTaskQueryService().getTaskInstanceById(t.getId());    
        	 List<OrganizationalEntity> oes =  tt.getPeopleAssignments().getBusinessAdministrators();
        	 List<OrganizationalEntity> ows =  tt.getPeopleAssignments().getPotentialOwners();
        	 System.out.println("getBusinessAdministrators:"+ oes);
        	 System.out.println("getPotentialOwners:"+ oes);
        });
		manager.disposeRuntimeEngine(engine);
	}
	
	
	@Test
	public void testGroupParamProcess() {
		logger.debug("==========testGroupProcess============");
		RuntimeManager manager = createRuntimeManager("com/icss/regie/workflow/demo/group/group-param.bpmn2");
		RuntimeEngine engine = manager.getRuntimeEngine(null);
		KieSession ksession = engine.getKieSession();
        Map<String,Object> parameters = new HashMap<String, Object>();  
        parameters.put("flag", "YES");
        parameters.put("currGroupId", "HR");
		ProcessInstance group = ksession.startProcess("com.icss.regie.workflow.demo.group-param",parameters);
        TaskSummary task;
        List<TaskSummary> list ;
        TaskService taskService = engine.getTaskService();
        //AddPeopleAssignmentsCommand command = new AddPeopleAssignmentsCommand(identityProvider.getName(), taskId, type, orgEntities, removeExisting))
        //taskService.execute(command);
        System.out.println("ProcessInstance : "+ group);
        
        
        list = taskService.getTasksAssignedAsPotentialOwner("mary", "en-UK");
        
        
        System.out.println("mary has task : "+ list.size());
        
        
        list = taskService.getTasksAssignedAsPotentialOwner("manager", "en-UK");
        System.out.println("manager has task : "+ list.size());
        
        list = taskService.getTasksAssignedAsPotentialOwner("john", "en-UK");
        System.out.println("john has task : "+ list.size());

		manager.disposeRuntimeEngine(engine);
	}
	
	@Test
	public void testMultipleInstance() {
		logger.debug("==========testMultipleInstance============");
		RuntimeManager manager = createRuntimeManager("com/icss/regie/workflow/demo/multiple/multiple-instance.bpmn2");
		RuntimeEngine engine = manager.getRuntimeEngine(null);
		KieSession ksession = engine.getKieSession();
        Map<String,Object> parameters = new HashMap<String, Object>();  

        //List assigneeList = Arrays.asList(new OrganizationalEntity[] {new UserImpl("john"),new UserImpl("mary"),new UserImpl("krisv"),new UserImpl("salaboy")});
        List assigneeList = Arrays.asList(new String[] {"john","mary","krisv","salaboy"});
        parameters.put("assigneeList", assigneeList);
		ProcessInstance group = ksession.startProcess("com.icss.regie.workflow.demo.multiple.multiple-instance",parameters);
        TaskSummary task;
        List<TaskSummary> list ;
        TaskService taskService = engine.getTaskService();
        System.out.println("ProcessInstance : "+ group);
        

        list = taskService.getTasksAssignedAsPotentialOwner("john", "en-UK");
        System.out.println("john has task : "+ list.size());
        if(list.size()>0) {
            Map<String,Object> decision = new HashMap<String,Object>();
            decision.put("decision", true);
            task = list.get(0);
            taskService.start(task.getId(), "john");
            taskService.complete(task.getId(), "john", decision);
        }
        
        list = taskService.getTasksAssignedAsPotentialOwner("mary", "en-UK");
        System.out.println("mary has task : "+ list.size());
        if(list.size()>0) {
            Map<String,Object> decision = new HashMap<String,Object>();
            decision.put("decision", true);
            task = list.get(0);
            taskService.start(task.getId(), "mary");
            taskService.complete(task.getId(), "mary", decision);
        }
        //list = taskService.getTasksAssignedAsPotentialOwner("mary", "en-UK");
        //System.out.println("mary has task : "+ list.size());
        
        list = taskService.getTasksAssignedAsPotentialOwner("krisv", "en-UK");
        System.out.println("krisv has task : "+ list.size());
        if(list.size()>0) {
            Map<String,Object> decision = new HashMap<String,Object>();
            decision.put("decision", true);
            task = list.get(0);
            taskService.start(task.getId(), "krisv");
            taskService.complete(task.getId(), "krisv", decision);
        }
        
        list = taskService.getTasksAssignedAsPotentialOwner("salaboy", "en-UK");
        System.out.println("salaboy has task : "+ list.size());
        if(list.size()>0) {
            Map<String,Object> decision = new HashMap<String,Object>();
            decision.put("decision", true);
            task = list.get(0);
            taskService.start(task.getId(), "salaboy");
            taskService.complete(task.getId(), "salaboy", decision);
        }
        
        list = taskService.getTasksAssignedAsPotentialOwner("manager", "en-UK");
        System.out.println("manager has task : "+ list.size()); 
        
		manager.disposeRuntimeEngine(engine);
	}
	
	@Test
	public void testMultipleInstanceSub() {
		logger.debug("==========testMultipleInstanceSub============");
		RuntimeManager manager = createRuntimeManager("com/icss/regie/workflow/demo/multiple/multiple-instance-sub.bpmn2");
		RuntimeEngine engine = manager.getRuntimeEngine(null);
		KieSession ksession = engine.getKieSession();
        Map<String,Object> parameters = new HashMap<String, Object>();  
        
        List assigneeList = Arrays.asList(new String[] {"john","mary","krisv","salaboy"});
        //List assigneeList = Arrays.asList(new String[] {"john"});
        parameters.put("list", assigneeList);
        
		ProcessInstance group = ksession.startProcess("com.icss.regie.workflow.demo.multiple-instance-sub",parameters);
        TaskSummary task;
        List<TaskSummary> list ;
        TaskService taskService = engine.getTaskService();
        System.out.println("ProcessInstance : "+ group);
        
        list = taskService.getTasksAssignedAsPotentialOwner("john", "en-UK");
        System.out.println("john has task : "+ list.size());

        list = taskService.getTasksAssignedAsPotentialOwner("mary", "en-UK");
        System.out.println("mary has task : "+ list.size());
        
        if(list.size()>0) {
            task = list.get(0);
            taskService.start(task.getId(), "mary");
            taskService.complete(task.getId(), "mary", null);
        }
        list = taskService.getTasksAssignedAsPotentialOwner("mary", "en-UK");
        System.out.println("mary has task : "+ list.size());
        
        list = taskService.getTasksAssignedAsPotentialOwner("krisv", "en-UK");
        System.out.println("krisv has task : "+ list.size());
        
        list = taskService.getTasksAssignedAsPotentialOwner("salaboy", "en-UK");
        System.out.println("salaboy has task : "+ list.size());
        
        list = taskService.getTasksAssignedAsPotentialOwner("manager", "en-UK");
        System.out.println("manager has task : "+ list.size()); 
        
        list = taskService.getTasksAssignedAsPotentialOwner("sales-rep", "en-UK");
        System.out.println("sales-rep has task : "+ list.size()); 

		manager.disposeRuntimeEngine(engine);
	}
}

