package com.microservices.practice.microservices_project.exceptions;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDetails {

	public ErrorDetails(LocalDate timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	private LocalDate timestamp;
	private String message;
	private String details;
}
