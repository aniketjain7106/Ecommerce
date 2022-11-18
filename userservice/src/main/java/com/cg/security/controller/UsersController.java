package com.cg.security.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.security.model.Users;
import com.cg.security.service.UsersService;


@RestController
@RequestMapping("/users")
//@CrossOrigin(origins = "http://localhost:4200")
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
//	@PostMapping("/add")
//	public ResponseEntity<Users> addUser(@RequestBody Users user) throws EmptyInputException {
//		return new ResponseEntity<Users>(service.addUser(user), HttpStatus.ACCEPTED);
////		return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.addBooks(book));
//	}
	
//	@GetMapping("/get")
//	public ResponseEntity<Optional<List<Users>>> getAllUsers() throws NoUsersAvailableException {
//		return new ResponseEntity<Optional<List<Users>>>(service.getAllUsers(), HttpStatus.OK);
//	}
	
//	@GetMapping("/get/{username}")
//	public ResponseEntity<Optional<Users>> getUserById(@PathVariable("username") String username) throws UserNotFoundException {
//		return new ResponseEntity<Optional<Users>>(service.getUserByUsername(username), HttpStatus.ACCEPTED);
//	}
	
//	@DeleteMapping("/delete/{username}")
//	public ResponseEntity<String> deleteUser(@PathVariable("username") String username) throws UserNotFoundException {
//		String msg = "Deleted Succesfully ID: " + username;
//		service.deleteUser(username);
//		return new ResponseEntity<String>(msg, HttpStatus.ACCEPTED);
//	}
	
	@GetMapping ("/{username}")
    public ResponseEntity<Users> getUserByUsername(@PathVariable ("username") String username){
        Users user =  usersService.getUserByUsername(username);
        if(user != null) {
        	return new ResponseEntity<Users>(
        			user,
        			HttpStatus.OK);
        }
        return new ResponseEntity<Users>(
        		HttpStatus.NOT_FOUND);
    }
	
	@DeleteMapping(value = "/{username}")
    public ResponseEntity<Users>  deleteUser(@PathVariable ("username") String username) {
		Users user =  usersService.getUserByUsername(username);
		System.out.println(user.getEmail());
    	if(user.getEmail()!= null) {
    		usersService.deleteUser(username);
        	return new ResponseEntity<Users>(
        			HttpStatus.OK);
        }
        return new ResponseEntity<Users>(
        		HttpStatus.NOT_FOUND);
    }
	
//	@PutMapping("/update/{username}")
//	public ResponseEntity<String> updateUser(@PathVariable("username") String username,@RequestBody Users user) throws UserNotFoundException {
//		service.updateUser(username, user);
//		return new ResponseEntity<String>("User Updated Successfully !", HttpStatus.ACCEPTED);
//	}

}
