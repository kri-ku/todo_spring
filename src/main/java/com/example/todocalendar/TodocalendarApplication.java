package com.example.todocalendar;

import java.time.LocalDate;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

			log.info("saving couple notes");
			repository.save(new Note(LocalDate.now(), LocalTime.now(), "just having fun", true));
			repository.save(new Note(LocalDate.now(), LocalTime.of(8, 40), "pet the cat", false));
			repository.save(new Note(LocalDate.now(), LocalTime.of(12, 40), "go to the store", false));
			repository.save(new Note(LocalDate.now(), LocalTime.of(22, 15), "go to the apple store", false));

			repository.save(
					new Note(LocalDate.of(2021, 9, 2), LocalTime.of(9, 15), "pet the cat AGAIN AND AGAIN", false));
			repository.save(new Note(LocalDate.of(2020, 9, 2), LocalTime.of(9, 15), "omat synttärit", false));
			repository.save(
					new Note(LocalDate.now().plusDays(1), LocalTime.of(9, 15), "huomiselle yksi tapahtuma", false));

			log.info("fetch all notes");
			for (Note note : repository.findAll()) {
				log.info(note.toString());
			}

			// (String username, String passwordHash, String email, String role
			log.info("making users");
			UserEntity user1 = new UserEntity("user", "$2a$04$eKMN1Hf4GbAlz86ZPoIH9eQGxpJTBfQIuml5HgqEsfTHWwqguTALu",
					"timo@timo.fi", "USER");
			UserEntity user2 = new UserEntity("admin", "$2a$04$tI3cgRKNX.IDZf4Hft85Lua4cbdvH2uxHji8z6vvKMeH1ZPwQqyUa",
					"kipa@kipa.fi", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
		};

	}

}
