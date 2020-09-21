package com.icss.regie.workflow.demo.first;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.jbpm.test.JBPMHelper;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeEnvironmentBuilder;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.manager.RuntimeManagerFactory;
import org.kie.api.task.TaskService;
import org.kie.api.task.model.TaskSummary;

public class XORProcessMain {

    public static void main(String[] args) {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieBase kbase = kContainer.getKieBase("xor");

        RuntimeManager manager = createRuntimeManager(kbase);
        RuntimeEngine engine = manager.getRuntimeEngine(null);
        KieSession ksession = engine.getKieSession();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("recipient", "test");
        params.put("dept", "a");
        ksession.startProcess("com.icss.regie.workflow.demo.xor", params);

        TaskService taskService = engine.getTaskService();
        TaskSummary task;
        List<TaskSummary> list = taskService.getTasksOwned("mingshu", "zh-CN");
        if (list.size() > 0) {
            System.out.println("mingshu has task");
            task = list.get(0);
            taskService.start(task.getId(), "mingshu");
            taskService.complete(task.getId(), "mingshu", null);
        }
        list = taskService.getTasksOwned("xiaoming", "zh-CN");
        if (list.size() > 0) {
            System.out.println("xiaoming has task");
            task = list.get(0);
            taskService.start(task.getId(), "xiaoming");
            taskService.complete(task.getId(), "xiaoming", null);
        }

        manager.disposeRuntimeEngine(engine);
        System.exit(0);
    }

    private static RuntimeManager createRuntimeManager(KieBase kbase) {
        JBPMHelper.startH2Server();
        JBPMHelper.setupDataSource();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("org.jbpm.persistence.jpa");
        RuntimeEnvironmentBuilder builder = RuntimeEnvironmentBuilder.Factory.get()
                .newDefaultBuilder().entityManagerFactory(emf)
                .knowledgeBase(kbase);
        return RuntimeManagerFactory.Factory.get()
                .newSingletonRuntimeManager(builder.get(), "com.icss.regie.workflow.demo:xor:1.0.0");
    }

}
