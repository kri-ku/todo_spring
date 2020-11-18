package com.example.todocalendar.domain;

import javax.validation.constraints.NotEmpty;

public class SignupForm {

	@NotEmpty
	private String username = "";

	@NotEmpty
	private String email = "";

	@NotEmpty
	private String password = "";

	@NotEmpty
	private String passwordmatch = "";
	
	@NotEmpty
	private String role = "USER";

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordmatch() {
		return passwordmatch;
	}

	public void setPasswordmatch(String passwordmach) {
		this.passwordmatch = passwordmach;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	

}
