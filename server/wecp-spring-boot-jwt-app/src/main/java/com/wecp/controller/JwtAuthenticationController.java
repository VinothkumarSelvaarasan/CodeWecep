package com.wecp.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wecp.config.JwtTokenUtil;
import com.wecp.model.JwtRequest;
import com.wecp.model.JwtResponse;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
			throws Exception {

		//authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = jwtInMemoryUserDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
			if(userDetails == null) {
				return ResponseEntity.unprocessableEntity().build();
			}
			
			if(userDetails != null && !userDetails.getPassword().equalsIgnoreCase(""+authenticationRequest.getPassword().hashCode())) {
				return ResponseEntity.unprocessableEntity().build();
			}
		authenticate(userDetails);

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(UserDetails userDetails) throws Exception {
		Objects.requireNonNull(userDetails);
		Objects.requireNonNull(userDetails.getUsername());
		Objects.requireNonNull(userDetails.getPassword());
		
//		if(!username.equals("test")) {
//			throw new Exception("INVALID_CREDENTIALS");
//		}
//		
//		if(!password.equals("12345")) {
//			throw new Exception("INVALID_CREDENTIALS");
//		}

//		try {
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//		} catch (DisabledException e) {
//			throw new Exception("USER_DISABLED", e);
//		} catch (BadCredentialsException e) {
//			throw new Exception("INVALID_CREDENTIALS", e);
//		}
	}
}