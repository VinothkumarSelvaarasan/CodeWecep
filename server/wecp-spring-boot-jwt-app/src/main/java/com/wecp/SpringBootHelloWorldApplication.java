package com.wecp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wecp.entities.User;
import com.wecp.repos.UserRepository;

@SpringBootApplication
public class SpringBootHelloWorldApplication implements CommandLineRunner{
	
	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootHelloWorldApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		User admin = new User();
		admin.setUserId("admin.user");
		admin.setPassword("12345");
		admin.setRole("ADMIN");
			if(userRepository.findByUserId(admin.getUserId())  == null){
				userRepository.save(admin);
			}
		
		
		User customer = new User();
		customer.setUserId("John");
		customer.setPassword("12345");
		customer.setRole("CLIENT");
		if(userRepository.findByUserId(customer.getUserId())  == null){
			userRepository.save(customer);
		}
		
		
	}
}