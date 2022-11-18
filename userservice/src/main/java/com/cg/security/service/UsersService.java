package com.cg.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cg.security.exception.Universal;
import com.cg.security.model.Users;
import com.cg.security.repository.UsersRepository;


@Service
public class UsersService {
	
	@Autowired
	private UsersRepository usersRepository;
	
	
	public Users getUserByUsername(String username) {
    	List<Users> users = usersRepository.findAll();
		for (Users user : users) {
			if (username.equals(user.getUsername())) {
				return user;}
		}
		throw new Universal("username not valid");
    }

	//delete user
	public void deleteUser(String username) {
		usersRepository.deleteById(username);
	}


}