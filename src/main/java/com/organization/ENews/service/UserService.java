package com.organization.ENews.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import com.organization.ENews.entity.User;
import com.organization.ENews.exceptions.ErrorCode;
import com.organization.ENews.exceptions.InvalidUserException;
import com.organization.ENews.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
    org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(!user.isEnabled()) {
            throw new InvalidUserException("User account not enabled",200);
        }
        java.util.List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
        if(!user.getRoles().isEmpty())
            user.getRoles().stream().forEach(role->{
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            });
        return user;
    }

	public void registerUser(User user) {

		boolean userExistancy = userRepository.existsByUsername(user.getUsername());
		if (userExistancy) {
			throw new InvalidUserException( ErrorCode.DUPLICATE_USER.getErrorDesc(),
											ErrorCode.DUPLICATE_USER.toString(),
											ErrorCode.DUPLICATE_USER.getErrorCode());
		}
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String strDate= formatter.format(date);
		user.setDatecreated(strDate);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);

	}
	

}
