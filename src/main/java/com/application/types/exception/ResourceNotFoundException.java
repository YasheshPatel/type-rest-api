package com.application.types.exception;

/**
 * ResourceNotFoundException to handle  Not Found Exception
 * @author Yashesh Patel
 */
public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String msg) {
		super(msg);
	}
}
