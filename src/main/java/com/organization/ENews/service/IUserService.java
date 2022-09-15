package com.organization.ENews.service;


import com.organization.ENews.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {
	
	void registerUser(User user);

}
