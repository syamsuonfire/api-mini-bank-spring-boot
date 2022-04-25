package com.hijrabank.model;



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

    @NotNull
    @Size(max = 50)
    private String name;

    @NotNull
    private String address;

    @NotNull
    @Temporal(TemporalType.DATE)
	private Date birthDate;
	
    @OneToOne(fetch = FetchType.EAGER,
        cascade = {
                CascadeType.MERGE,
                CascadeType.REFRESH
            }, mappedBy = "person", orphanRemoval = true)
    private Account account;

	public Person() {
	}

	public Person(Long id, @NotNull @Size(max = 50) String name, @NotNull String address, @NotNull Date birthDate,
			Account account) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.birthDate = birthDate;
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

}

