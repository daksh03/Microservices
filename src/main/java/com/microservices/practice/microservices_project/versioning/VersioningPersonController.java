package com.microservices.practice.microservices_project.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {
	
	@GetMapping("/v1/person")
	public PersonV1 getFirstVersionofPerson() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getSecondVersionofPerson() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	@GetMapping(path = "/person", params = "version=1")
	public PersonV1 getFirstVersionofPersonRequestParameter() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
	public PersonV1 getFirstVersionofPersonAcceptHeader() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(path="/person", params= "version=2")
	public PersonV2 getSecondVersionofPersonRequestParameter() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	@GetMapping(path="/person", headers= "X-API-VERSION=1")
	public PersonV1 getSecondFirstVersionofPersonRequestHeader() {
		return new PersonV1("Bob Charlie");
	}
	@GetMapping(path="/person", headers= "X-API-VERSION=2")
	public PersonV2 getSecondVersionofPersonRequestHeader() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	

}
