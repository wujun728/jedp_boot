package org.jbpm.demo.evaluation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.test.JbpmJUnitBaseTestCase;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.TaskService;
import org.kie.api.task.model.TaskSummary;

public class ProcessTest extends JbpmJUnitBaseTestCase{

	public ProcessTest() {
		super(true, true);
	}
	
	@Test
	public void testEvaluationProcess() {
		RuntimeManager manager = createRuntimeManager("org/jbpm/demo/evaluation.bpmn");
		RuntimeEngine engine = manager.getRuntimeEngine(null);
		KieSession ksession = engine.getKieSession();
		TaskService taskService = engine.getTaskService();
		
		// start a new process instance
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("employee", "krisv");
		params.put("reason", "Yearly performance evaluation");
		ProcessInstance processInstance = ksession.startProcess("org.jbpm.demo.evaluation", params);
        System.out.println("Process started ...");
        
		// complete Self Evaluation
		List<TaskSummary> tasks = taskService.getTasksAssignedAsPotentialOwner("krisv", "en-UK");
		assertEquals(1, tasks.size());
		TaskSummary task = tasks.get(0);
		System.out.println("'krisv' completing task " + task.getName() + ": " + task.getDescription());
		taskService.start(task.getId(), "krisv");
		Map<String, Object> results = new HashMap<String, Object>();
		results.put("performance", "exceeding");
		taskService.complete(task.getId(), "krisv", results);

		// john from HR
		tasks = taskService.getTasksAssignedAsPotentialOwner("john", "en-UK");
		assertEquals(1, tasks.size());
		task = tasks.get(0);
		System.out.println("'john' completing task " + task.getName() + ": " + task.getDescription());
		taskService.claim(task.getId(), "john");
		taskService.start(task.getId(), "john");
		results = new HashMap<String, Object>();
		results.put("performance", "acceptable");
		taskService.complete(task.getId(), "john", results);
				
		// mary from PM
		tasks = taskService.getTasksAssignedAsPotentialOwner("mary", "en-UK");
		assertEquals(1, tasks.size());
		task = tasks.get(0);
		System.out.println("'mary' completing task " + task.getName() + ": " + task.getDescription());
		taskService.claim(task.getId(), "mary");
		taskService.start(task.getId(), "mary");
		results = new HashMap<String, Object>();
		results.put("performance", "outstanding");
		taskService.complete(task.getId(), "mary", results);

		assertProcessInstanceCompleted(processInstance.getId(), ksession);
		System.out.println("Process instance completed");
		
		manager.disposeRuntimeEngine(engine);
		manager.close();
	}

}
