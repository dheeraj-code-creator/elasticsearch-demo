package com.spirngboot.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spirngboot.rest.entity.User;
import com.spirngboot.rest.service.UserService;

@RestController
@RequestMapping(value = "/userinfo")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping(value ="/alluser", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUser(){
		return userService.getAllUserInfo();
	}
	
	@GetMapping(value ="/alluser/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUserByName(@PathVariable String userName){
		return userService.getUserDataByName(userName);
	}
	
	@GetMapping(value ="/alluser/{userName}/{address}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUserByNameAndAddress(@PathVariable String userName, @PathVariable String address){
		return userService.getUserDataByNameAndAddress(userName, address);
	}
	
}
