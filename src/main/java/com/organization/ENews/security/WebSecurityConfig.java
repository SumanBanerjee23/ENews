package com.organization.ENews.security;

import com.organization.ENews.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	JwtAuthEntryPoint jwtAuthEntryPoint;
	
	@Autowired
	IUserService userService;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.cors().and().csrf().disable()
		.authorizeRequests()
		.antMatchers("/user/save").permitAll()
		.antMatchers("/swagger-ui.html").permitAll()
		.antMatchers("/api-docs").permitAll()
		.antMatchers("/swagger-resources/**").permitAll()
		.antMatchers("/webjars/**").permitAll()
		.antMatchers("/asserts/**").permitAll()
		.antMatchers("/*.js").permitAll()
		.antMatchers("/*.css").permitAll()
		.antMatchers("/*.png").permitAll()
		.antMatchers("/*.svg").permitAll()
		.antMatchers("/*.ttf").permitAll()
		.antMatchers("/*.woff2").permitAll()
		.antMatchers("/*.eot").permitAll()
		.antMatchers("/favicon.ico").permitAll()
//		.anyRequest().authenticated()
		.and()
		.exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint).and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class );
	}
	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
		authenticationManagerBuilder
		.userDetailsService(userService)
		.passwordEncoder(passwordEncoder());
	}
	@Bean
	@Override
	public org.springframework.security.authentication.AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public JwtTokenFilter authenticationJwtTokenFilter(){
		return new JwtTokenFilter();
	}
	
	@Bean
	public org.springframework.security.crypto.password.PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}
	
}
