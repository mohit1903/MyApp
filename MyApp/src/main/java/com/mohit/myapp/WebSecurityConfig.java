package com.mohit.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		
		http.authorizeRequests()
			.antMatchers("/user").access("hasRole('user')")
			.antMatchers("/admin").access("hasRole('admin')")
			.and()
				.formLogin().loginPage("/login")
				.defaultSuccessUrl("/home")
				.failureUrl("/login?error")				
			.and()
				.logout().logoutSuccessUrl("/login?logout"); 
		
	}
}