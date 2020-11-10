package com.example.todocalendar.model;


import java.time.LocalDate;
import java.time.LocalTime;

public class Note {
	private long id;
	private LocalDate date;
	private LocalTime time;
	private String content;
	private Boolean important;
	// private String repeat;

	public Note() {
	}

	public Note(LocalDate date, LocalTime time, String content, Boolean important) {
		super();
		this.date = date;
		this.time = time;
		this.content = content;
		this.important = important;
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

	@Override
	public String toString() {
		return "Note [date=" + date + ", time=" + time + ", content=" + content + ", important=" + important + "]";
	}

}

