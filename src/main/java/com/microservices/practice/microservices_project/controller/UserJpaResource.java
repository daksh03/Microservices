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

import com.microservices.practice.microservices_project.repositories.PostRepo;
import com.microservices.practice.microservices_project.repositories.UserJpaRepo;
import com.microservices.practice.microservices_project.user.Post;
import com.microservices.practice.microservices_project.user.User;
import com.microservices.practice.microservices_project.user.UserJpa;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {
	
	
	@Autowired
	private UserJpaRepo userJpaRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	protected UserJpaResource(UserJpaRepo userJpaRepo, PostRepo postRepo) {
		this.userJpaRepo = userJpaRepo;
		this.postRepo = postRepo;
	}
	
	@GetMapping("/jpa-users")
	public List<UserJpa> retrieveUsers() {
		return userJpaRepo.findAll();
	}
	@GetMapping("/jpa-users/{id}/posts")
	public List<Post> retrievePostsofUser(@PathVariable int id) {
		Optional<UserJpa> user = userJpaRepo.findById(id);
		if(user.isEmpty()) 
			throw new UserNotFoundException("id:"+id);
		return user.get().getPosts();
	}
	
	@GetMapping("/jpausers/{id}")
	public EntityModel<UserJpa> retrieveUser(@PathVariable Integer id) {
		Optional<UserJpa> user = userJpaRepo.findById(id);
		if(user.isEmpty()) 
			throw new UserNotFoundException("id:"+id);
		EntityModel<UserJpa> entityModel = EntityModel.of(user.get());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveUsers());
		entityModel.add(link.withRel("all-users"));
		return entityModel;
	}
	
	@DeleteMapping("/jpa-users/{id}")
	public void deleteUser(@PathVariable Integer id) {
		userJpaRepo.deleteById(id);	
	}
	
	@PostMapping("/jpa-addUser")
	public ResponseEntity<Object> register(@Valid @RequestBody UserJpa user) {
		UserJpa savedUser = userJpaRepo.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	@PostMapping("/addPost/{id}")
	public Post createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
		Optional<UserJpa> user = userJpaRepo.findById(id);
		post.setUser(user.get());
		Post savedPost = postRepo.save(post);
		
		return savedPost;
	}

}
