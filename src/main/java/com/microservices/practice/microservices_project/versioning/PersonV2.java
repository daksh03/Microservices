package com.microservices.practice.microservices_project.versioning;

import lombok.Getter;

@Getter	
public class PersonV2 {
	private Name name;

	public PersonV2(Name name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "PersonV2 [name=" + name + "]";
	}

}
