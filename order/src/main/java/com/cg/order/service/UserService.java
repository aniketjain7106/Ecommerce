package com.cg.order.service;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cg.order.model.Users;


@Service
//@FeignClient(name="${service.inventory}", path="${api.inventory.item}")
@FeignClient(name="user-service",url = "http://localhost:8814/users")
public interface UserService {
	
	@GetMapping("/{username}")
	Users getUserByUsername(@PathVariable("username") String username);

}
