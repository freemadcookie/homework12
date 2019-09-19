package com.github.siberianintegrationsystems.restApp;

import com.github.siberianintegrationsystems.restApp.data.JournalRepository;
import com.github.siberianintegrationsystems.restApp.entity.Journal;
import com.github.siberianintegrationsystems.restApp.service.JournalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class RestAppApplication {

	@Autowired
	private JournalRepository journalRepository;

	public static void main(String[] args) {
		SpringApplication.run(RestAppApplication.class, args);
	}

	@PostConstruct
	private void initData() {
		Journal journal = new Journal();
		journal.setId(JournalServiceImpl.QUESTIONS_JOURNAL_ID);
		journal.setName("Вопросы");
		journal.setDefaultPageSize(15L);
		journalRepository.save(journal);

		Journal journalSessions = new Journal();
		journalSessions.setId(JournalServiceImpl.SESSION_JOURNAL_ID);
		journalSessions.setName("Сессии");
		journalSessions.setDefaultPageSize(10L);
		journalRepository.save(journalSessions);
	}
}
