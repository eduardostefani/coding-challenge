package com.adidas.digitalcreation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
public class DigitalCreationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 219236151269702929L;

	public DigitalCreationException(String message) {
        super(message);
    }

    public DigitalCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
