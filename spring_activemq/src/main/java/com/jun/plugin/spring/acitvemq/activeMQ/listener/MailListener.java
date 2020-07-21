package com.jun.plugin.spring.acitvemq.activeMQ.listener;

import org.springframework.transaction.annotation.Transactional;

import com.jun.plugin.spring.acitvemq.po.Mail;

public class MailListener {
	@Transactional
	public void displayMail(Mail mail) {
		System.out.println("从ActiveMQ取出一条消息：");
		System.out.println(mail.toString());
		}
}
