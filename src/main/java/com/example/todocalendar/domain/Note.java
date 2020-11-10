package com.example.todocalendar.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.data.annotation.Id;

@Entity
public class Note {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	private LocalTime time;
	private String content;
	private Boolean important;
	private Boolean done = false;

	public Note() {
	}

	public Note(LocalDate date, LocalTime time, String content, Boolean important) {

		this.date = date;
		this.time = time;
		this.content = content;
		this.important = false;
		this.done = false;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Note [id=" + id + ", date=" + date + ", time=" + time + ", content=" + content + ", important="
				+ important + ", done=" + done + "]";
	}

}
