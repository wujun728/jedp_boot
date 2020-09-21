package controller.exception;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;


public class LoginfailHandler implements AuthenticationFailureHandler {

	public void onAuthenticationFailure(HttpServletRequest arg0,
			HttpServletResponse response, AuthenticationException ex)
			throws IOException, ServletException {
		if (ex instanceof org.springframework.security.authentication.BadCredentialsException) {
			response.sendRedirect("/Spring-security/html/errorpassword.html");
		}
		if(ex instanceof org.springframework.security.authentication.InternalAuthenticationServiceException){
			response.sendRedirect("/Spring-security/html/errorusername.html");
		}
	}

}
