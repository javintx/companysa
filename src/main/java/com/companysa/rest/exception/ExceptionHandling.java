package com.companysa.rest.exception;

import com.companysa.usecase.domain.exception.NotAgreeWithTermsAndConditions;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandling extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotAgreeWithTermsAndConditions.class)
	public ResponseEntity<ApiError> handleNotAgreeWithTermsAndConditionsException(HttpServletRequest request, Exception ex) {
		return createErrorResponseEntity(ex, request, HttpStatus.BAD_REQUEST);
	}

	private ResponseEntity<ApiError> createErrorResponseEntity(Exception ex, HttpServletRequest request, HttpStatus status) {
		ApiError apiError = createError(ex, request, status);
		String errorMessage = toLogError(apiError);
		System.err.println(errorMessage);
		return new ResponseEntity<>(apiError, status);
	}

	private String toLogError(ApiError apiError) {
		String logAsString = "";
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			logAsString = objectMapper.writeValueAsString(apiError);
		} catch (JsonProcessingException e) {
			System.out.println(e.getMessage());
		}
		return logAsString;
	}

	private ApiError createError(
		Exception exception, HttpServletRequest httpServletRequest, HttpStatus errorCode) {
		return ApiError.builder()
			.withStatus(errorCode.value())
			.withError(errorCode.getReasonPhrase())
			.withException(exception.getClass().getCanonicalName())
			.withMessage(exception.getLocalizedMessage())
			.inPath(httpServletRequest.getRequestURI())
			.build();
	}

	private static class ApiError {
		private final int status;
		private final String error;
		private final String exception;
		private final String message;
		private final String path;

		private ApiError(final int status,
						 final String error,
						 final String exception,
						 final String message,
						 final String path) {
			this.status = status;
			this.error = error;
			this.exception = exception;
			this.message = message;
			this.path = path;
		}

		static ApiErrorBuilder builder() {
			return new ApiErrorBuilder();
		}

		public int status() {
			return status;
		}

		public String error() {
			return error;
		}

		public String exception() {
			return exception;
		}

		public String message() {
			return message;
		}

		public String path() {
			return path;
		}

		private static class ApiErrorBuilder {
			private int status;
			private String error;
			private String exception;
			private String message;
			private String path;

			public ApiErrorBuilder withStatus(int status) {
				this.status = status;
				return this;
			}

			public ApiErrorBuilder withError(String error) {
				this.error = error;
				return this;
			}

			public ApiErrorBuilder withException(String exception) {
				this.exception = exception;
				return this;
			}

			public ApiErrorBuilder withMessage(String message) {
				this.message = message;
				return this;
			}

			public ApiErrorBuilder inPath(String path) {
				this.path = path;
				return this;
			}

			ApiError build() {
				return new ApiError(status, error, exception, message, path);
			}
		}
	}
}


