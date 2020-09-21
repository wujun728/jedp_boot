package cn.springmvc.util.router;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.springmvc.hibernate.common.router.Router;

/**
 * @author Vincent.wang
 * 
 *         <p>
 *         production为生产环境，development为测试环境
 *         </p>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml", "classpath:/spring/applicationContext-dao.xml" })
@ActiveProfiles("development")
public class RouterTest {

    @Test
    public void router() {
        CamelContext context = new DefaultCamelContext();
        try {
            context.addRoutes(new Router());
            context.start();

            Thread.sleep(1000000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void mqRouter() {
        CamelContext context = new DefaultCamelContext();
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");
        try {
            context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
            context.addRoutes(new Router());
            context.start();
            Thread.sleep(1000000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
