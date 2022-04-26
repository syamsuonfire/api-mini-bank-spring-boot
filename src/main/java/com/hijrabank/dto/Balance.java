package com.hijrabank.dto;

import java.math.BigDecimal;

public class Balance {
    private BigDecimal balance;


    
    public Balance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
