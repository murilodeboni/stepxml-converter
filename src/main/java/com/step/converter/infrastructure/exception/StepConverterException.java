package com.step.converter.infrastructure.exception;

import com.step.converter.domain.dto.ErrorResponse;
import lombok.Getter;

public class StepConverterException extends Exception {

	@Getter
	private ErrorResponse errorResponse;

	public StepConverterException( Exception exception ) {

		super( exception );

		errorResponse = null;
	}

	public StepConverterException( Exception exception, ErrorResponse errorResponse ) {

		super( exception );

		this.errorResponse = errorResponse;
	}
}