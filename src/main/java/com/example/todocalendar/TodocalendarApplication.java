package com.example.todocalendar;

import java.time.LocalDate;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.todocalendar.domain.Note;
import com.example.todocalendar.domain.NoteRepository;
import com.example.todocalendar.domain.UserEntity;
import com.example.todocalendar.domain.UserRepository;

@SpringBootApplication
public class TodocalendarApplication {
	private static final Logger log = LoggerFactory.getLogger(TodocalendarApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TodocalendarApplication.class, args);
	}

	@Bean
	public CommandLineRunner dbdemo(NoteRepository repository, UserRepository urepository) {
		return (args) -> {

			BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
			// String username, String passwordHash, String email, String role
			log.info("making users");
			UserEntity user1 = new UserEntity("user", bc.encode("user"),"timo@timo.fi", "USER");
			UserEntity user2 = new UserEntity("admin", bc.encode("admin"), "kipa@kipa.fi", "ADMIN");
			UserEntity user3 = new UserEntity("kristiina", bc.encode("happywifehappylife"),"kristiina@kristiina.fi", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			urepository.save(user3);

			// LocalDate date, LocalTime time, String content, Boolean important,UserEntity user
			log.info("saving couple notes");
			repository.save(new Note(LocalDate.now(), LocalTime.now(), "buy apples", true, user1));
			repository.save(new Note(LocalDate.now(), LocalTime.of(15, 20), "go to meet granny", false, user1));
			repository.save(new Note(LocalDate.now(), LocalTime.of(12, 45), " super important meeting", true, user1));
			repository.save(new Note(LocalDate.now(), LocalTime.of(22, 15), "Movies with Leon", false, user1));

			repository.save(
					new Note(LocalDate.of(2021, 9, 2), LocalTime.of(9, 00), "Book festival all day", false, user1));
			repository.save(new Note(LocalDate.of(2020, 9, 2), LocalTime.of(9, 15), "Pete's birthday!", false, user1));
			repository.save(
					new Note(LocalDate.now().plusDays(1), LocalTime.of(9, 15), "Tatjana's birthday", false, user2));

			log.info("fetch all notes");
			for (Note note : repository.findAll()) {
				log.info(note.toString());
			}

		};

	}

}
