package com.microservices.practice.microservices_project.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.microservices.practice.microservices_project.dao.UserDao;
import com.microservices.practice.microservices_project.repositories.UserRepo;
import com.microservices.practice.microservices_project.user.User;

import jakarta.validation.Valid;

@RestController
public class UserMongoResource {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserRepo userRepo;
	
	protected UserMongoResource(UserDao userDao, UserRepo userRepo) {
		this.userDao =  userDao;
		this.userRepo = userRepo;
	}
	
	@GetMapping("/mongo-users")
	public List<User> retrieveUsers() {
		return userRepo.findAll();
	}
	
	@GetMapping("/mongousers/{id}")
	public EntityModel<User> retrieveUser(@PathVariable Integer id) {
		Optional<User> user = userRepo.findById(id);
		if(user.isEmpty()) 
			throw new UserNotFoundException("id:"+id);
		EntityModel<User> entityModel = EntityModel.of(user.get());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveUsers());
		entityModel.add(link.withRel("all-users"));
		return entityModel;
	}
	
	@DeleteMapping("/mongo-users/{id}")
	public void deleteUser(@PathVariable Integer id) {
		userRepo.deleteById(id);	
	}
	
	@PostMapping("/mongo-addUser")
	public ResponseEntity<Object> register(@Valid @RequestBody User user) {
		User savedUser = userRepo.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

}
