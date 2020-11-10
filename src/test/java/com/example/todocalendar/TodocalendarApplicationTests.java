package com.example.todocalendar;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.todocalendar.web.NoteController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TodocalendarApplicationTests {

	@Autowired
	private NoteController controller;
	
	@Test
	void contextLoads() throws Exception{
		assertThat(controller).isNotNull();
	}

}
