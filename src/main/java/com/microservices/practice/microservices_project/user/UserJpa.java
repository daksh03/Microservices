package com.microservices.practice.microservices_project.user;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_details")
public class UserJpa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	@SequenceGenerator(name = "user_seq", sequenceName = "user_sequence", allocationSize = 1)
	private Integer id;

	@Size(min=2, message = "Name should have atleast 2 characters")
	private String name;
	
	@Past(message = "BirthDate should be in Past")
	private LocalDate birthDate;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Post> posts;
	
	 public UserJpa(Integer id, String name, LocalDate birthDate) {
	        super();
	        this.id = id;
	        this.name = name;
	        this.birthDate = birthDate;
	    }
	 
	 public UserJpa() {
	    }
}
