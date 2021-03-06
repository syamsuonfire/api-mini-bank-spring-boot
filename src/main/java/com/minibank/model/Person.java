package com.minibank.model;



import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "persons")
public class Person extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name is required")
	@Size(min = 3, message = "Name too short, min 3 characters")
	@Size(max = 50, message = "Name too long, max 50 characters")
    private String name;

    @NotNull(message = "Address is required")
    private String address;

    @NotNull(message = "Birthdate is required")
    @Temporal(TemporalType.DATE)
	private Date birthDate;

	private Double salary;

	private String hobby;

	
    @OneToOne(fetch = FetchType.EAGER,
        cascade = {
                CascadeType.MERGE,
                CascadeType.REFRESH
            }, mappedBy = "person", orphanRemoval = true)
    private Account account;

	public Person() {
	}



	public Person(Long id,
			@NotNull(message = "Name is required") @Size(min = 3, message = "Name too short, min 3 characters") @Size(max = 50, message = "Name too long, max 50 characters") String name,
			@NotNull(message = "Address is required") String address,
			@NotNull(message = "Birthdate is required") Date birthDate, Double salary, String hobby, Account account) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.birthDate = birthDate;
		this.salary = salary;
		this.hobby = hobby;
		this.account = account;
	}



	public Person(Long id, @NotNull @Size(max = 50) String name, @NotNull String address, @NotNull Date birthDate,
			Account account,Double salary, String hobby) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.birthDate = birthDate;
				this.salary = salary;
		this.hobby = hobby;
		this.account = account;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}


	public Double getSalary() {
		return salary;
	}



	public void setSalary(Double salary) {
		this.salary = salary;
	}



	public String getHobby() {
		return hobby;
	}



	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	
}

