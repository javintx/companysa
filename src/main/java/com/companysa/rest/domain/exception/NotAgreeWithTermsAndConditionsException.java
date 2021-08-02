package com.companysa.rest.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotAgreeWithTermsAndConditionsException extends RuntimeException {
	private final static String CAUSE = "User is not agree with terms and conditions";

	public NotAgreeWithTermsAndConditionsException() {
		super(CAUSE);
	}
}
