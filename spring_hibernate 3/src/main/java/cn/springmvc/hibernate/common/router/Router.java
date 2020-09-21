package cn.springmvc.hibernate.common.router;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.file.GenericFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.springmvc.hibernate.common.constants.Constants;
import cn.springmvc.hibernate.common.utils.PropertyConfigurer;

/**
 * @author Vincent.wang
 *
 */
@Component
public class Router extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        String pop = PropertyConfigurer.getValue(Constants.MAIL_POP);
        // String imap = PropertyConfigurer.getValue(Constants.MAIL_IMAP);
        String user = PropertyConfigurer.getValue(Constants.MAIL_USER);
        String password = PropertyConfigurer.getValue(Constants.MAIL_PASSWORD);

        /**
         * 邮件路由 <br>
         * consumer.delay=1000 意思是多久再请求一次收件箱 <br>
         * maxMessagesPerPoll 最多收多少封邮件,默认是未设计最大值 <br>
         * connectionTimeout 连接有效时长，单位秒
         */
        from("pop3://" + pop + "?username=" + user + "&password=" + password + "&consumer.delay=500&unseen=true").process(new MailProcessor()).to("log:email")// .to("log:newmail")
            .end();
        // from("imap://"+imap+"?username=" + user + "&password=" + password + "&consumer.delay=500&unseen=true").process(new MailProcessor()).to("log:email").end();

        /**
         * 文件路由<br>
         * delete=true 原文件删除 file:name
         */
        // from("file:E:/input?delay=3000&charset=UTF-8&noop=true&delete=true").process(new FileProcessor()).to("file:E:/output?charset=UTF-8&moveFailed=E:/output/error");

        /**
         * ActiveMQ路由
         * 
         */
        // from("jms:queue:" + Constants.QUEUE_FOO_BAR).process(new QueueProcessor()).to("file:E:/MQ?doneFileName=done-camel.txt");

        /**
         * Other ....<br>
         * 
         * FTP <br>
         * SFTP <br>
         * Log <br>
         * RSS <br>
         * SQL <br>
         * Bean <br>
         * JDBC <br>
         * MongoDB <br>
         * JPA <br>
         * Class <br>
         * Properties <br>
         * Solr <br>
         * Stream <br>
         * Timer <br>
         * Velocity <br>
         * Zookeeper <br>
         * XSLT <br>
         * Ref <br>
         * Quartz <br>
         * Quartz2 <br>
         * MyBatis <br>
         * Language <br>
         * Cache <br>
         * ........ ........ ........
         * 
         */
    }
}

/**
 * 邮件处理器
 * 
 * @author wangxin
 *
 */
class MailProcessor implements Processor {

    private final Logger log = LoggerFactory.getLogger(MailProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        // Message message = exchange.getIn().getBody(Message.class);
        // if (message != null) {
        // log.warn("# subject=【{}】", StringUtils.join(message.getFrom(), " , "));
        // log.warn("# mail address=【{}】", message.getSubject());
        // log.warn("# mail content=【{}】", message.getContent());
        // }
        Map<String, Object> parameter = exchange.getIn().getHeaders();
        if (!parameter.isEmpty()) {
            Iterator<Entry<String, Object>> iter = parameter.entrySet().iterator();
            Entry<String, Object> entry = null;
            while (iter.hasNext()) {
                entry = iter.next();
                log.warn("# header: name=[{}] , value=[{}]", entry.getKey(), entry.getValue());
            }
        }
    }
}

/**
 * 文件处理器
 * 
 * @author wangxin
 *
 */
class FileProcessor implements Processor {

    @SuppressWarnings("unchecked")
    @Override
    public void process(Exchange exchange) throws Exception {
        GenericFile<File> gf = exchange.getIn().getBody(GenericFile.class);
        File file = gf.getFile();
        PrintStream ps = new PrintStream(System.out);
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line = null;
        while ((line = br.readLine()) != null) {
            ps.println(line);
        }
        ps.close();
        br.close();
    }
}

/**
 * Queue处理器
 * 
 * @author Vincent.wang
 *
 */
class QueueProcessor implements Processor {

    private static final Logger log = LoggerFactory.getLogger(QueueProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        log.warn("## Queue message body=[{}]", exchange.getIn().getBody(String.class));
        Map<String, Object> parameter = exchange.getIn().getHeaders();
        if (!parameter.isEmpty()) {
            Iterator<Entry<String, Object>> iter = parameter.entrySet().iterator();
            Entry<String, Object> entry = null;
            while (iter.hasNext()) {
                entry = iter.next();
                log.warn("# header: name=[{}] , value=[{}]", entry.getKey(), entry.getValue());
            }
        }
    }

}
