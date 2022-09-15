package com.organization.ENews.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {
	
	public static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(JwtAuthEntryPoint.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex)
			throws IOException, ServletException {
		LOGGER.error("Unauthorised Error, Message - {}",ex.getMessage());
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Error - > Unauthorised");
	
	}

}
