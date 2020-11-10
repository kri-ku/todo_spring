package com.example.todocalendar;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.todocalendar.domain.Note;
import com.example.todocalendar.domain.NoteRepository;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class NoteRepositoryTest {
	
	@Autowired
	private NoteRepository repository;
	
	@Test
	public void createNewNote() {
		Note note = new Note(LocalDate.now(), LocalTime.now(), "Käy mummolassa kahvilla", true);
		repository.save(note);
		assertThat(note.getId()).isNotNull();
	}
	
	@Test
	public void deleteNote() {
		Note note = new Note(LocalDate.now(), LocalTime.now(), "Käy mummolassa kahvilla", true);
		repository.save(note);
		repository.deleteById(note.getId());
		Iterable<Note> allbooks = repository.findAll();
		assertThat(allbooks).doesNotContain(note);
	}
	
	 @Test
	 public void findById() {
		 Note note = new Note(LocalDate.now(), LocalTime.now(), "Käy mummolassa kahvilla", true);
		 repository.save(note);
		 assertThat(repository.findById(note.getId())).isNotNull();
		 
	 }

}
