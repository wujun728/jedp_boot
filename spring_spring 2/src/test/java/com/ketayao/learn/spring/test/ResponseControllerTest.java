package com.ketayao.learn.spring.test;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ResponseControllerTest {

	public static void main(String[] args) {
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> entity = template.getForEntity(
				"http://localhost:8080/quickstart/task",
				String.class);
		String body = entity.getBody();
		MediaType contentType = entity.getHeaders().getContentType();
		HttpStatus statusCode = entity.getStatusCode();
		System.out.println("statusCode:[" + statusCode + "]");
	}
}