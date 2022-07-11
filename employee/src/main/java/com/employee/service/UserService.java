package com.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.model.User;
import com.employee.repo.UserRepository;

@Service
public class UserService{

	@Autowired
	UserRepository ur;
	
	public User findByUserName(String userName) {
		return ur.findByUserName(userName);
	}
	public User saveUser(User user) {
		return ur.save(user);
	}
}
