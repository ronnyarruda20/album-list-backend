package com.list.music.handler;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.list.music.pojo.ErrorResponse;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private String localResources = "/messages.properties";

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleResourceNotFoundException(Exception rnfe, HttpServletRequest request) {
		String message = this.getMesangem(rnfe.getMessage());
		ErrorResponse errorDetail = new ErrorResponse(message);
		return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);

	}

	public String getMesangem(String key) {

		try {
			Properties props = new Properties();
			props.load(this.getClass().getResourceAsStream(this.localResources));
			return props.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Erro desconhecido";
	}
}
