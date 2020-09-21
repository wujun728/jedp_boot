/**
 * Copyright (C) 2016 Juno Inc., All Rights Reserved.
 */
package com.jyl.security.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jyl.security.exception.ValidateCodeAuthenticationException;
import com.jyl.util.randomCode.RandomCodeUtils;

/**
 *
 * @author Long, E-mail:jyl0401@163.com
 * @date 2016年7月13日 上午10:27:51
 */
public class ValidateCodeAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    
	private boolean postOnly = true;
    private String validateCodeParameter;
    private boolean validateCodeSwitch = true;
    
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

		if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        
		//-----------------------validateCode authentication------------------------
		if(validateCodeSwitch){
			String randomCode = obtainValidateCode(request);
	        if (randomCode == null) {
	        	randomCode = "";
	        }
	        randomCode = randomCode.trim();
	        
	        if(false == RandomCodeUtils.checkRandomCode(request, randomCode)){
	        	throw new ValidateCodeAuthenticationException("验证码不正确");
	        }
		}

        String username = obtainUsername(request);
        String password = obtainPassword(request);

        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        username = username.trim();

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }


	protected String obtainValidateCode(HttpServletRequest request) {
        return request.getParameter(validateCodeParameter);
    }
	
	public String getValidateCodeParameter() {
		return validateCodeParameter;
	}

	public void setValidateCodeParameter(String validateCodeParameter) {
		this.validateCodeParameter = validateCodeParameter;
	}


	public boolean isValidateCodeSwitch() {
		return validateCodeSwitch;
	}


	public void setValidateCodeSwitch(boolean validateCodeSwitch) {
		this.validateCodeSwitch = validateCodeSwitch;
	}

}
