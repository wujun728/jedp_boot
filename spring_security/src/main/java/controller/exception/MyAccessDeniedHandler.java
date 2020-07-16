package controller.exception;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class MyAccessDeniedHandler implements AccessDeniedHandler{

	public void handle(HttpServletRequest arg0, HttpServletResponse response,
			AccessDeniedException arg2) throws IOException, ServletException {
		response.sendRedirect("/Spring-security/html/norule.html");
		
	}

}
