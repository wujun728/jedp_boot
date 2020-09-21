package org.springframework.data.mongodb.examples.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.examples.hello.HelloMongo;

public class UseXMLFileConfigServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello BAE World !");
		ConfigurableApplicationContext context = null;
		// use @Configuration using Java:
        //context = new ClassPathXmlApplicationContext("META-INF/spring/bootstrap.xml");

		// use XML application context:
        context = new ClassPathXmlApplicationContext("META-INF/spring/applicationContext.xml");

        HelloMongo hello = context.getBean(HelloMongo.class);
        String results = hello.run();
        resp.getWriter().println(results);
			
	}
}
