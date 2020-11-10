package com.example.todocalendar.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.todocalendar.domain.Note;
import com.example.todocalendar.domain.NoteRepository;

@CrossOrigin
@RestController
public class RESTNoteController {
	
	@Autowired
	private NoteRepository repository;
	
	//get all
	@GetMapping("/notes")
	public List<Note> getAll() {
		return (List<Note>)repository.findAll();
	}
	
	// get one
	@GetMapping("/notes/{id}")
	public Optional<Note> findNote(@PathVariable Long id) {
		return repository.findById(id);
	}
	
	//post new
	@PostMapping("/notes")
	public Note postnote(@RequestBody Note newnote) {
		return newnote;
	}
	
	//delete one
	@DeleteMapping("/notes/{id}")
	public void deleteNote(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	//update Note
	@PutMapping("/notes/{id}")
	public Note updateNote(@RequestBody Note newnote, @PathVariable Long id) {
		Optional<Note> oldnote = repository.findById(id);
		Note note = oldnote.get();
		note.setContent(newnote.getContent());
		note.setDate(newnote.getDate());
		note.setImportant(newnote.getImportant());
		note.setTime(newnote.getTime());
		return repository.save(note);
	}

}
