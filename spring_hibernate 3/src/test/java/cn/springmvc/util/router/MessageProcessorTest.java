package cn.springmvc.util.router;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.springmvc.hibernate.common.message.MessageProcessor;

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
public class MessageProcessorTest {

    @Autowired
    private MessageProcessor messageProcessor;

    @Test
    public void sendProcess() {
        try {
            String message = "Hello , Apache Camel";
            messageProcessor.sendProcess(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void receiverProcess() {
        try {
            messageProcessor.receiverProcess();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
