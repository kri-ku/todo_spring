package com.example.todocalendar.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.todocalendar.domain.Note;
import com.example.todocalendar.domain.NoteRepository;
import com.example.todocalendar.domain.UserEntity;
import com.example.todocalendar.domain.UserRepository;

@Controller
public class NoteController {

	@Autowired
	private NoteRepository repository;
	@Autowired
	private UserRepository urepository;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MMM yyyy", new Locale("en"));
	
	//get current user as UserEntity
	private UserEntity getCurrUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		UserEntity curruser = urepository.findByUsername(username); 
		return curruser;
	}
	

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	// show all notes starting from today
	@GetMapping({ "/", "/notelist" })
	public String notelist(Model model) {
		model.addAttribute("notes", repository.findByUserAndDateAfterOrderByDate(getCurrUser(),LocalDate.now().minusDays(1), Sort.by("time")));
		return "notelist";
	}

	// show add form
	@RequestMapping(value = "/add")
	public String addNote(Model model) {
		model.addAttribute("note", new Note());
		return "form";
	}

	// show notes today, done and not-done
	@GetMapping("/todaylist")
	public String listtoday(Model model) {
		model.addAttribute("notesnotdone", repository.findAllByUserAndDateAndDoneOrderByTime(getCurrUser(),LocalDate.now(), false));
		model.addAttribute("notesdone", repository.findAllByUserAndDateAndDoneOrderByTime(getCurrUser(),LocalDate.now(), true));
		model.addAttribute("date", LocalDate.now().format(formatter));
		return "todaylist";

	}
	
	// show notes tomorrow, done and not-done
	@GetMapping("/tomorrowlist")
	public String listtomorrow(Model model) {
		model.addAttribute("notesnotdone", repository.findAllByUserAndDateAndDoneOrderByTime(getCurrUser(),LocalDate.now().plusDays(1), false));
		model.addAttribute("notesdone", repository.findAllByUserAndDateAndDoneOrderByTime(getCurrUser(),LocalDate.now().plusDays(1), true));
		model.addAttribute("date", LocalDate.now().plusDays(1).format(formatter));
		return "tomorrowlist";

	}

	// save new note to database
	@PostMapping("/save")
	public String save(Note note) {
		note.setUser(getCurrUser());
		repository.save(note);
		return "redirect:/notelist";
	}

	// delete note
	@PostMapping("/delete/{id}")
	public String deleteNote(@PathVariable("id") Long noteId) {
		repository.deleteById(noteId);
		return "redirect:/";
	}

	// mark note as done or not-done and send two lists to view, return to today's view
	@GetMapping("/donetoday/{id}")
	public String markAsDone(@PathVariable("id") Long noteId, Model model) {
		Optional<Note> markable = repository.findById(noteId);
		Note note = markable.get();

		if (note.getDone() == true) {
			note.setDone(false);
		} else {
			note.setDone(true);
		}
		repository.save(note);
		
		model.addAttribute("notesnotdone", repository.findAllByUserAndDateAndDoneOrderByTime(getCurrUser(),LocalDate.now(), false));
		model.addAttribute("notesdone", repository.findAllByUserAndDateAndDoneOrderByTime(getCurrUser(),LocalDate.now(), true));
		return "redirect:/todaylist";
	}
	
	// mark note as done or not-done and send two lists to view, return to tomorrows view
	@GetMapping("/donetomorrow/{id}")
	public String markAsDoneTomorrow(@PathVariable("id") Long noteId, Model model) {
		Optional<Note> markable = repository.findById(noteId);
		Note note = markable.get();

		if (note.getDone() == true) {
			note.setDone(false);
		} else {
			note.setDone(true);
		}
		repository.save(note);
		model.addAttribute("notesnotdone", repository.findAllByUserAndDateAndDoneOrderByTime(getCurrUser(),LocalDate.now().plusDays(1), false));
		model.addAttribute("notesdone", repository.findAllByUserAndDateAndDoneOrderByTime(getCurrUser(),LocalDate.now().plusDays(1), true));
		return "redirect:/tomorrowlist";
	}

	// show notes before today
	@RequestMapping(value = "/previousnotes")
	public String showOldNotes(Model model) {
		model.addAttribute("notes", repository.findByUserAndDateBeforeOrderByDate(getCurrUser(), LocalDate.now(),Sort.by("time")));
		return "previousnotes";
	}

	//show form for editing
	@GetMapping("/edit/{id}")
	public String editNote(@PathVariable("id") Long noteId, Model model) {
		model.addAttribute("note", repository.findById(noteId));
		return "editNote";
	}
	
}
