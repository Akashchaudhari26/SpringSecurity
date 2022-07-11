package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.model.LoginRequest;
import com.employee.model.LoginResponse;
import com.employee.securityConfigure.MyUserDetailsService;
import com.employee.utils.JwtUtils;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	MyUserDetailsService userDetailsService;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@PostMapping("/")
	public LoginResponse login(@RequestBody LoginRequest loginRequest) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
		} catch (BadCredentialsException e) {
			// TODO Auto-generated catch block
			throw new Exception("Bad Credentials");
		}
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUserName());
		
		String jwt = jwtUtils.generateToken(userDetails);
		return new LoginResponse(jwt);
	}
}
