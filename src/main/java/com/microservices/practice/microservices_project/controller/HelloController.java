package com.microservices.practice.microservices_project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/hello/{name}")
	public HelloBean helloname(@PathVariable String name) {
		return new HelloBean(String.format("Hello World, %s ", name));
	}

}
