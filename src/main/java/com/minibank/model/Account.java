package com.minibank.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name = "accounts")
public class Account extends AuditModel  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @OneToOne(fetch = FetchType.EAGER,
        cascade = {
                CascadeType.MERGE,
                CascadeType.REFRESH
            })
	@JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false, unique = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	@JsonProperty("person_id")
	private Person person;

    @NotNull(message = "Balance is required")
	@NumberFormat(pattern = "#,###,###,###.##")
	@Min(value = 0, message = "Balance must be greater than 0")
	private BigDecimal balance;
	
    @NotNull(message = "DailyDebitLimit is required")
	@NumberFormat(pattern = "#,###,###,###.##")
	@Min(value = 0, message = "DailyDebitLimit must be greater than 0")
	private BigDecimal dailyDebitLimit;
	
	@NotNull
	private boolean active;
	
	

	public Account() {
	}

	public Account(Long id, Person person, @NotNull BigDecimal balance, @NotNull BigDecimal dailyDebitLimit,
			@NotNull boolean active) {
		this.id = id;
		this.person = person;
		this.balance = balance;
		this.dailyDebitLimit = dailyDebitLimit;
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getDailyDebitLimit() {
		return dailyDebitLimit;
	}

	public void setDailyDebitLimit(BigDecimal dailyDebitLimit) {
		this.dailyDebitLimit = dailyDebitLimit;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
