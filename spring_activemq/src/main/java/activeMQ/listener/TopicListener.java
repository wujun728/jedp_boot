package activeMQ.listener;

import org.springframework.transaction.annotation.Transactional;

import po.Mail;

public class TopicListener {
	@Transactional
	public void displayTopic(Mail mail) {
		System.out.println("从ActiveMQ的Topic：mytopic中取出一条消息：");
		System.out.println(mail.toString());
		}
}
