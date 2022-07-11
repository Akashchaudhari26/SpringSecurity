package com.employee.securityConfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	AuthorizationRequestFilter authorizationFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//		.withUser("employee")
//		.password("$2a$10$RRpYiLXT7LWxllZA4SBt3OO9XLFT01Y49oSAfjlhynWo0HPVZFULe")
//		.roles("employee")
//		.and()
//		.withUser("admin")
//		.password("$2a$10$RRpYiLXT7LWxllZA4SBt3OO9XLFT01Y49oSAfjlhynWo0HPVZFULe")
//		.roles("admin");
		
		auth.userDetailsService(userDetailsService);
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
		//return new BCryptPasswordEncoder(10);
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeHttpRequests()
		.antMatchers("/employee/get/*").hasAnyRole("ADMIN","USER")
		.antMatchers("/employee/get").hasRole("ADMIN")
		.antMatchers("/employee/wish").permitAll()
		.antMatchers(HttpMethod.POST, "/user/*").permitAll()
		.antMatchers(HttpMethod.POST,"/login/").permitAll()
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.csrf().disable();
		
		http.addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	public AuthenticationManager getAuthenticationManagerBean() throws Exception {
		return super.authenticationManager();
	}
}
