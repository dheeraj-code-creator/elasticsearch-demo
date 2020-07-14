package com.spirngboot.rest.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spirngboot.rest.entity.User;

@Repository
public interface UserRepository {

	List<User> findAllUserDetailsFromElastic();

	List<User> findUserDetailByName(String userName);
}
