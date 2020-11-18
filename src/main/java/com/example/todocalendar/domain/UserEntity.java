package com.example.todocalendar.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class UserEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="user_id", nullable= false, updatable=false)
	private Long user_id;
	
	//username should be unique
	@Column(name= "username", nullable= false, unique=true)
	private String username;
	
	@Column(name="password", nullable= false)
	private String passwordHash;
	
	@Column(name="email", nullable= false)
	private String email;
	
	@Column(name="role", nullable= false)
	private String role;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Note> notes;
	
	public UserEntity() {}

	public UserEntity(String username, String passwordHash, String email, String role) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.email = email;
		this.role = role;
	}

	
	public UserEntity(String username, String passwordHash, String email, String role, List<Note> notes) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.email = email;
		this.role = role;
		this.notes = notes;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "UserEntity [user_id=" + user_id + ", username=" + username + ", passwordHash=" + passwordHash
				+ ", email=" + email + ", role=" + role + ", notes=" + notes + "]";
	}
	
	
	
}
