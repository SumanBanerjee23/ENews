package com.organization.ENews.controller;

import javax.validation.Valid;

import com.organization.ENews.entity.User;
import com.organization.ENews.security.JwtProvider;
import com.organization.ENews.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@Api("User")
public class SignupController extends GlobalExceptionHandlerController{

	@Autowired
    private IUserService userService;
	
	@Autowired
    private JwtProvider jwtProvider;
	
	@Autowired
    org.springframework.security.authentication.AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user){
		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		org.springframework.security.core.context.SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwtToken=jwtProvider.generateToken(authentication);
		org.springframework.security.core.userdetails.UserDetails userDetails=(org.springframework.security.core.userdetails.UserDetails) authentication.getPrincipal();
		return ResponseEntity.ok(jwtToken);
		
	}
	

	@PostMapping("/save")
	@ApiOperation("Create a new user")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
		userService.registerUser(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	

}
