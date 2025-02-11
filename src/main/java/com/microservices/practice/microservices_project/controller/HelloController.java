package com.microservices.practice.microservices_project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.practice.microservices_project.bean.HelloBean;

@RestController
public class HelloController {
	
	@GetMapping("/hello")
	public String helloWorld() {
		return "Hello";
	}
	
	@GetMapping("/hello-bean")
	public HelloBean hello() {
		return new HelloBean("Hello Bean");
	}

}
