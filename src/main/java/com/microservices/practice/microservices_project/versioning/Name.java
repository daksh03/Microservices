package com.microservices.practice.microservices_project.versioning;

import lombok.Getter;

@Getter
public class Name {
	private String firstName;
	private String lastName;
	@Override
	public String toString() {
		return "Name [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	public Name(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

}
