package com.wecp.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.wecp.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
    
   public User findByUserId(String item);
    
   List<User> findAll();
   
}