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

	// all of notes are find by user so that user only sees notes that he/she has
	// made -->

	// Find notes after date and sort them by date, used in all notes - page
	// In controller sort them also by time and set the "day after" yesterday
	public List<Note> findByUserAndDateAfterOrderByDate(UserEntity user, LocalDate date, Sort sort);

	// find notes before this date and sort them, used in previous notes -page
	// In controller sort them also by time
	public List<Note> findByUserAndDateBeforeOrderByDate(UserEntity user, LocalDate date, Sort sort);


	// find by date and done and sort by time
	public List<Note> findAllByUserAndDateAndDoneOrderByTime(UserEntity user, LocalDate date, Boolean done);


}
