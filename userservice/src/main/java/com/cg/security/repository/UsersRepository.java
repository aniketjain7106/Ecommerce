package com.cg.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cg.security.model.Users;

public interface UsersRepository extends MongoRepository<Users, String> {
	
    public Users getUserByUsername(String username);

}
