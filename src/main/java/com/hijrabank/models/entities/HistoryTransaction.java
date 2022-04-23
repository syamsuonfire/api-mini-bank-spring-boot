package com.hijrabank.models.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


@Entity
@Table(name = "history-transactions") 
public class HistoryTransaction implements Serializable {
        
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memberTransaction;

    @OneToMany(
            mappedBy = "member",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Transaction> transactions;

    public HistoryTransaction() {
    }

    public HistoryTransaction(Long id, String memberTransaction, List<Transaction> transactions) {
        this.id = id;
        this.memberTransaction = memberTransaction;
        this.transactions = transactions;
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

    public String getMemberTransaction() {
        return memberTransaction;
    }

    public void setMemberTransaction(String memberTransaction) {
        this.memberTransaction = memberTransaction;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    

}
