package com.wecp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wecp.entities.User;
import com.wecp.repos.UserRepository;

@RestController
//@CrossOrigin()
public class UserController {
	
	@Autowired
	UserRepository repository;
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> saveUser(@RequestBody User user)
			throws Exception {

		Objects.requireNonNull(user);
		Objects.requireNonNull(user.getUserId());
		Objects.requireNonNull(user.getPassword());
		User usr = repository.findByUserId(user.getUserId());
			if(usr == null) {
				repository.save(user);
			}
			else {
				usr.setPassword(user.getPassword());
				usr.setRole(user.getRole());
				repository.save(usr);
			}
		Map<String, String> body = new HashMap<>();
		body.put("message", "User added successfully");
		return ResponseEntity.ok(body);

	}
	
	@RequestMapping(value = "/fetchusers", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> fetchusers()
			throws Exception {

		List<User> users =  repository.findAll();
		return ResponseEntity.ok(users);
	}

}