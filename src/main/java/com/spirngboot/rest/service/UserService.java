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
	
	public List<User> getAllUserInfo() {
		
		return userRepository.findAllUserDetailsFromElastic();
	}

	public List<User> getUserDataByName(String userName) {
		return userRepository.findAllUserDataByNameFromElastic(userName);
	}

	public List<User> getUserDataByNameAndAddress(String userName, String address) {
		return userRepository.findAllUserDataByNameAndAddressFromElstic(userName,address);
	}


}
