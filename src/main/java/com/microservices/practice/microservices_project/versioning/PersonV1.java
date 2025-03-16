package com.microservices.practice.microservices_project.versioning;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PersonV1 {
	private String name;

	public PersonV1(String name) {
		super();
		this.name = name;
	}

}
