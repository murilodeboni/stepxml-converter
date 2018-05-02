package com.step.converter.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class ErrorResponse {

	private String code;

	private String message;

	private String description;
}