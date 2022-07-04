package com.application.types.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Yashesh Patel
 */
@Getter
@AllArgsConstructor
public class ErrorMessage {

	private String message;
	private HttpStatus status;
	private LocalDateTime timestamp;

}
