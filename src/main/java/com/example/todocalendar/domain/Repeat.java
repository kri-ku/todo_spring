package com.example.todocalendar.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// NOT USED ANYWHERE
// tells how often the note is repeated: daily, weekly, monthly, yearly
@Entity
public class Repeat {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	private String time;
	
	
	public Repeat() {}
	
	public Repeat(String time) {
		this.time = time;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	

}
