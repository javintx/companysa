package com.companysa.usecase.domain.exception;

public class NotAgreeWithTermsAndConditions extends RuntimeException {
	private final static String CAUSE = "User is not agree with terms and conditions";

	public NotAgreeWithTermsAndConditions() {
		super(CAUSE);
	}
}
