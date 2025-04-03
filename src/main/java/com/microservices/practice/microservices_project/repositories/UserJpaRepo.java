package com.microservices.practice.microservices_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.practice.microservices_project.user.UserJpa;

@Repository
public interface UserJpaRepo extends JpaRepository<UserJpa, Integer>{

}
