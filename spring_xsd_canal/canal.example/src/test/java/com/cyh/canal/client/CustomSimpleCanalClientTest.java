package com.cyh.canal.client;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CustomSimpleCanalClientTest {

    @Test
    public void testStartClient() throws Exception {
        String xml = "classpath:conf/applicationContext.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { xml });
        System.out.println(context.getBean("singleCanalClient"));
        System.in.read();
    }
}