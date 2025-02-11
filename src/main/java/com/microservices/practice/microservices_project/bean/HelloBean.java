package com.microservices.practice.microservices_project.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloBean {
	private String message;

	@Override
	public String toString() {
		return "HelloBean [message=" + message + "]";
	}

	public HelloBean(String message) {
		super();
		this.message = message;
	}
}
