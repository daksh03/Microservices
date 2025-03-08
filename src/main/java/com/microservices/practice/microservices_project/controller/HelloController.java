package com.microservices.practice.microservices_project.controller;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.practice.microservices_project.bean.HelloBean;

@RestController
public class HelloController {
	
	private MessageSource messageSource;
	
	public HelloController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	
	@GetMapping("/hello")
	public String helloWorld() {
		return "Hello";
	}
	@GetMapping("/hello-inter")
	public String helloWorldinternational() {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null,"Default Message", locale);
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
