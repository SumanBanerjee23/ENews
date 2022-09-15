package com.organization.ENews.security;

import com.organization.ENews.repository.UserRepository;
import com.organization.ENews.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
	
	@Autowired
    JwtProvider jwtProvider;
	@Autowired
	UserService userService;
	@Autowired
    private UserRepository userRepository;

	public static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(JwtTokenFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterchain)
			throws ServletException, IOException {
		try{
			String jwtToken=getTokenFromRequest(request);
			if(jwtToken != null && jwtProvider.validateToken(jwtToken)){
				String username = jwtProvider.getUsernameFromToken(jwtToken);
				UserDetails userDetails = userService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken usernamePasswordToken = new UsernamePasswordAuthenticationToken(
						userDetails,null,userDetails.getAuthorities());
				org.springframework.security.core.context.SecurityContextHolder.getContext().setAuthentication(usernamePasswordToken);
			}
		}
		catch(Exception ex){
			LOGGER.error("Error -> " + ex);
		}
		filterchain.doFilter(request, response);
		
	}

	private String getTokenFromRequest(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
		if(authHeader!=null && authHeader.startsWith("Bearer")){
			authHeader =  authHeader.replace("Bearer","");
		}
		return authHeader;
	}
	

}
