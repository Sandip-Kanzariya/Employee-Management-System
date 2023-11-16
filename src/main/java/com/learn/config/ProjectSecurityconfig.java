package com.learn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.learn.service.EmployeeService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)  
public class ProjectSecurityconfig {
//	
//	@Autowired
//	private UserDetailsService euserDetailsService;
//	
//	
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(euserDetailsService).passwordEncoder(passwordEncoder());
//	}
//	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		 http
	        .csrf().disable()
	        .authorizeHttpRequests()
	        .requestMatchers("/").hasRole("ADMIN")
	        .anyRequest().authenticated()
	        .and()
	        .formLogin();
		 
		return http.build();
	}
	
	// Password Configuration
//	@Bean
//	public static PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
	
}
