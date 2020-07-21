package com.jun.plugin.spring.acitvemq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jun.plugin.spring.acitvemq.po.Mail;
import com.jun.plugin.spring.acitvemq.service.Producer;
@Transactional
@Service("topic")
public class TopicImpl implements Producer{
	@Autowired
	@Qualifier("jmsTopicTemplate")
	private JmsTemplate jmsTopicTemplate;
	
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTopicTemplate = jmsTemplate;
	}
	public void sendMail(Mail mail) {
		jmsTopicTemplate.convertAndSend(mail);
	}

}
