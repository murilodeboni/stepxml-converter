package com.step.converter.domain.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ErrorResponse {

	private Long code;

	private String message;

	private String description;
}