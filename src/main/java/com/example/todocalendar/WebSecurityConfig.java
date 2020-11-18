package com.example.todocalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.todocalendar.web.UserDetailServiceImpl;



@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailServiceImpl userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		//.csrf().disable() // this disabling makes using Postman possible to test REST
		.authorizeRequests()
		//.antMatchers("/api/userEntities", "/api/notes/**/user").hasRole("ADMIN") THIS DOES NOT WORK
		.antMatchers("/css/**","/registration", "/saveuser").permitAll()
		.and()
		.authorizeRequests()
		.anyRequest().authenticated()	//all requests need authentication
		.and()
		.formLogin()
		.loginPage("/login")
		.defaultSuccessUrl("/todaylist", true) // when login is success go to today's page
		.permitAll()
		.and()
		.logout()
		.permitAll();
	}
	
	// using user entities
	// encrypting passwords
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new
				BCryptPasswordEncoder());
	}

}
