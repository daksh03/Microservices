package com.microservices.practice.microservices_project.user;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "user_details")
@Getter
@Setter
public class User {
	
	@Id
	private Integer id;
	
	@Size(min=2, message = "Name should have atleast 2 characters")
	@JsonProperty("user_name")
	private String name;
	
	@Past(message = "Birth Date should be in the Past")
	@JsonProperty("birth-date")
	private LocalDate birthDate;
	

	public User(Integer id, String name, LocalDate birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}

}
