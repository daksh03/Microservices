package com.microservices.practice.microservices_project.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.microservices.practice.microservices_project.user.User;

@Repository
public interface UserRepo extends MongoRepository<User,Integer> {

}
