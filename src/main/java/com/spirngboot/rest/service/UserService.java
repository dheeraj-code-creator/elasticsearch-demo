package com.spirngboot.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spirngboot.rest.entity.User;
import com.spirngboot.rest.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsersInfo() {
		return userRepository.findAllUserDetailsFromElastic();
	}

	public List<User> getUserByName(String userName) {
		return userRepository.findUserDetailByName(userName);
	}
}
