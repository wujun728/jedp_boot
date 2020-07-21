package com.jun.plugin.spring.acitvemq.service;

import com.jun.plugin.spring.acitvemq.po.Mail;


public interface Producer {
	public void sendMail(Mail mail);
}
