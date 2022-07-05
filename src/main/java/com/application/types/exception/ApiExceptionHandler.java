package com.application.types.exception;

import java.time.LocalDateTime;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Yashesh Patel
 */
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	public static final String ERROR_MESSAGE_TEMPLATE = "Error: %s requested uri: %s";

	@Override
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error(String.format(ERROR_MESSAGE_TEMPLATE, ex.getMessage(), request.getDescription(false)), ex);
		return buildResponseEntity(
				new ErrorMessage(ex.getMessage(), HttpStatus.METHOD_NOT_ALLOWED, LocalDateTime.now()));
	}

	@Override
	@ResponseStatus(HttpStatus.NOT_FOUND)
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		log.error(String.format(ERROR_MESSAGE_TEMPLATE, ex.getMessage(), request.getDescription(false)), ex);
		return buildResponseEntity(new ErrorMessage(ex.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now()));
	}

	private ResponseEntity<Object> buildResponseEntity(ErrorMessage errMsg) {
		return new ResponseEntity<>(errMsg, errMsg.getStatus());
	}
}
