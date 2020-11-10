package com.example.todocalendar.domain;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface NoteRepository extends CrudRepository<Note, Long> {

	// find note by id
	public Optional<Note> findById(Long id);

	// find all notes
	public List<Note> findAll();

	// Find notes after date and sort them by date, used in all notes - page
	// In controller sort them also by time and set the "day after" yesturday
	public List<Note> findByDateAfterOrderByDate(LocalDate date, Sort sort);

	// find notes before this date and sort them, used in previous notes -page
	// In controller sort them also by time
	public List<Note> findByDateBeforeOrderByDate(LocalDate date, Sort sort);
	
	// find by date and done and sort by time
	public List<Note> findAllByDateAndDoneOrderByTime(LocalDate date, Boolean done);

	// Find all notes and sort them by date and time, NOT USED
	//public List<Note> findAllByOrderByDate(Sort sort);

	// public List<Note> findByStartDateBeforeOrderByDate(LocalDate date,Sort sort);

	// tätä ei vielä käytetä
	//public List<Note> findByImportant(Boolean important);

	// public List<Note> findByDate(LocalDate date);

	// find notes by date and order them by time, used to show notes today and
	// tomorrow
	//public List<Note> findAllByDateOrderByTime(LocalDate date);

	// public List<Note> findAllByDateOrderByDate(LocalDate date); //kokeilu

	// public List<Note> findAllByOrderByDate();

	// tätä ei käytetä mihinkään just nyt
	//public List<Note> findAllByDoneOrderByTime(Boolean done);

	
}
