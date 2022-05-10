package com.minibank;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.minibank.model.Person;
import com.minibank.repository.PersonRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JpaPersonUnitTest {

	@Autowired
	PersonRepository personRepository;

	@Test
	public void createPerson() throws ParseException {

		Person p1 = new Person();
		p1.setAddress("kompleks Asia Serasi No 100");
		p1.setName("Wawan Setiawan");
		p1.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1978-04-14"));
		p1.setCreatedAt(new Date());
		p1.setUpdatedAt(new Date());
		
		Person personSaved = personRepository.save(p1);

		assertNotNull(personSaved);

	}

}