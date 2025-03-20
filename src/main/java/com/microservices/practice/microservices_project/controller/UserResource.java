package com.microservices.practice.microservices_project.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.microservices.practice.microservices_project.dao.UserDao;
import com.microservices.practice.microservices_project.user.User;

import jakarta.validation.Valid;

@RestController
public class UserResource {
	
	@Autowired
	private UserDao userDao;
	
	public UserResource(UserDao userDao) {
		this.userDao =  userDao;
	}
	
	@GetMapping("/users")
	public List<User> retrieveUsers() {
		return userDao.findAll();
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable Integer id) {
		User user = userDao.findOne(id);
		if(user == null) 
			throw new UserNotFoundException("id:"+id);
		EntityModel<User> entityModel = EntityModel.of(user);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveUsers());
		entityModel.add(link.withRel("all-users"));
		return entityModel;
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable Integer id) {
		userDao.deleteById(id);	
	}
	
	@PostMapping("/addUser")
	public ResponseEntity<Object> register(@Valid @RequestBody User user) {
		User savedUser = userDao.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

}
