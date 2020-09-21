package cn.springmvc.hibernate.common.message;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.springmvc.hibernate.common.constants.Constants;

/**
 * 消息发送处理器
 * 
 * @author Vincent.wang
 *
 */
@Component
public class MessageProcessor {

    private static final Logger log = LoggerFactory.getLogger(MessageProcessor.class);

    public static void main(String[] args) {
        MessageProcessor pro = new MessageProcessor();
        pro.sendProcess("Hello , Apache Camel!");
    }

    private Connection getConnection() throws JMSException {
        // ConnectionFactory ：连接工厂，JMS 用它创建连接，构造ConnectionFactory实例对象，此处采用ActiveMq的实现
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");

        // Connection ：JMS 客户端到JMS Provider 的连接，构造从工厂得到连接对象
        Connection connection = connectionFactory.createConnection();

        return connection;
    }

    public void sendProcess(String message) {
        Connection connection = null;// Connection ：JMS 客户端到JMS Provider 的连接

        try {

            connection = getConnection();// 构造从工厂得到连接对象
            connection.start();// 启动

            // Session： 一个发送或接收消息的线程
            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);// 获取操作连接

            // 获取session注意参数值FirstQueue是一个服务器的queue，须在在ActiveMq的console配置
            // Destination ：消息的目的地;
            Destination destination = session.createQueue(Constants.QUEUE_FOO_BAR);

            // MessageProducer：消息发送者
            MessageProducer producer = session.createProducer(destination);

            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);// 设置不持久化，此处学习，实际根据项目决定

            if (log.isDebugEnabled()) {
                log.debug("## message send body=[{}]", message);
            }
            // 构造消息，此处写死，项目就是参数，或者方法获取
            TextMessage text = session.createTextMessage(message);

            producer.send(text);// 发送消息

            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection)
                    connection.close();
            } catch (Throwable ignore) {
            }
        }
    }

    public void receiverProcess() {
        Connection connection = null;// Connection ：JMS 客户端到JMS Provider 的连接
        try {
            connection = getConnection();// 构造从工厂得到连接对象
            connection.start();// 启动

            // Session： 一个发送或接收消息的线程
            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);// 获取操作连接

            // 获取session注意参数值FirstQueue是一个服务器的queue，须在在ActiveMq的console配置
            // Destination ：消息的目的地;
            Destination destination = session.createQueue(Constants.QUEUE_FOO_BAR);

            MessageConsumer consumer = session.createConsumer(destination);

            while (true) {
                // 设置接收者接收消息的时间，为了便于测试，这里谁定为100s
                TextMessage message = (TextMessage) consumer.receive(100000);
                if (null != message) {
                    System.out.println("收到消息" + message.getText());
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection)
                    connection.close();
            } catch (Throwable ignore) {
            }
        }
    }

}