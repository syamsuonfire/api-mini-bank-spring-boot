package com.hijrabank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.hijrabank.model.Account;
import com.hijrabank.model.Person;
import com.hijrabank.repository.AccountRepository;
import com.hijrabank.repository.PersonRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JpaAccountUnitTest {

	@Autowired
	PersonRepository personRepository;

	@Autowired
	AccountRepository accountRepository;

	@Test
	public void createAccount() {

		Person p1 = new Person();
		p1.setAddress("kompleks Asia Serasi No 100");
		p1.setName("Wawan Setiawan");
		try {
			p1.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1997-15-02"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		p1.setCreatedAt(new Date());
		p1.setUpdatedAt(new Date());

		Person personSaved = personRepository.save(p1);

		Account ac = new Account();
		ac.setPerson(personSaved);
        ac.setBalance(BigDecimal.valueOf(0).movePointLeft(2));
		ac.setDailyDebitLimit(BigDecimal.valueOf(100000).movePointLeft(2));
		ac.setActive(true);
		ac.setCreatedAt(new Date());
		ac.setUpdatedAt(new Date());

		System.out.println(ac);
		Account accountSaved = accountRepository.save(ac);

		assertNotNull(accountSaved);
		assertEquals(accountSaved.getBalance(), BigDecimal.valueOf(0).movePointLeft(2));

	}
}