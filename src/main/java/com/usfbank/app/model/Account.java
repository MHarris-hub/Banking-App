package com.usfbank.app.model;

import java.math.BigDecimal;

public class Account {
    private String username;
    private String password;
    private BigDecimal balance;
    private boolean approvalStatus;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public boolean approvalStatus() {
        return approvalStatus;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setApprovalStatus(boolean approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
}