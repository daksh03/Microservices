package com.microservices.practice.microservices_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.practice.microservices_project.user.Post;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer>{

}
