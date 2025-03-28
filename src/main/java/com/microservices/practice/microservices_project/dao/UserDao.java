package com.microservices.practice.microservices_project.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.microservices.practice.microservices_project.repositories.UserRepo;
import com.microservices.practice.microservices_project.user.User;

@Component
public class UserDao {
	
	UserRepo userRepo;
	private static List<User> users = new ArrayList<>();
	private static int userCount = 0;
	static {
		users.add(new User(++userCount,"Adam",LocalDate.now().minusYears(30)));
		users.add(new User(++userCount,"Eve",LocalDate.now().minusYears(25)));
		users.add(new User(++userCount,"Jim",LocalDate.now().minusYears(20)));
	}
	
	public List<User> findAll() {
		return userRepo.findAll();
	}
	
	public User findOne(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id); 
		return users.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public void deleteById(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		users.removeIf(predicate);
	}
	
	public User save(User user) {
		
		return userRepo.save(user);
	}

}
