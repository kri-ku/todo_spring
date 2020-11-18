package com.example.todocalendar.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Note {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long note_id;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	private LocalTime time;
	private String content;
	private Boolean important;
	private Boolean done = false;

	@ManyToOne
	// @JsonIgnoreProperties ("note")
	@JoinColumn(name = "user_id")
	private UserEntity user;

	public Note() {
	}

	public Note(LocalDate date, LocalTime time, String content, Boolean important) {

		this.date = date;
		this.time = time;
		this.content = content;
		this.important = false;
		this.done = false;
	}

	public Note(LocalDate date, LocalTime time, String content, Boolean important, UserEntity user) {
		super();
		this.date = date;
		this.time = time;
		this.content = content;
		this.important = important;
		this.user = user;
	}

	public long getId() {
		return note_id;
	}

	public void setId(long id) {
		this.note_id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getImportant() {
		return important;
	}

	public void setImportant(Boolean important) {
		this.important = important;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

}
