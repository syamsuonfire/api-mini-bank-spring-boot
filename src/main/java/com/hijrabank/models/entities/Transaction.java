package com.hijrabank.models.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.sql.Date;

@Entity
@Table(name = "transactions") 
public class Transaction implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Status is required")
    private String status;

    private Integer amount;

    @Column(name = "transaction_date")
    private Date transactionDate;

    @ManyToOne
    private Member member;


    public Transaction() {
    }

    public Transaction(Long id, String status, Integer amount, Date transactionDate, Member member) {
        this.id = id;
        this.status = status;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.member = member;
    }


    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }


    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

}


