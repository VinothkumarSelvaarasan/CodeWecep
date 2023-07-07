package com.wecp.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wecp.repos.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		if ("javainuse".equals(username)) {
//			return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
//					new ArrayList<>());
//		} else {
//			throw new UsernameNotFoundException("User not found with username: " + username);
//		}
		com.wecp.entities.User user =  repository.findByUserId(username);
		 Collection<? extends GrantedAuthority> authorities
         = Arrays.asList(user.getRole()).stream()
         .map(authority -> new SimpleGrantedAuthority(authority))
         .collect(Collectors.toList());
			if(user != null) {
				return new User(user.getUserId(), ""+user.getPassword().hashCode(),
						getAuthority(user));
			}
			else {
				throw new UsernameNotFoundException("User not found with username: " + username);
			}
		
//		if ("test".equals(username)) {
//			return new User("javainuse", "123876243",
//					new ArrayList<>());
//		} else {
//			throw new UsernameNotFoundException("User not found with username: " + username);
//		}
		
		
	}
	
	 private Set<SimpleGrantedAuthority> getAuthority(com.wecp.entities.User user) {
	        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
	        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
	        return authorities;
	    }

}