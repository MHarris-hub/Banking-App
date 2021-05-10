package com.usfbank.app.model;

import java.math.BigDecimal;

public class Account {
    private int id;
    private BigDecimal balance;
    private boolean approvalStatus;

    public Account(int id, BigDecimal balance, boolean approvalStatus) {
        this.id = id;
        this.balance = balance;
        this.approvalStatus = approvalStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public boolean approvalStatus() {
        return approvalStatus;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setApprovalStatus(boolean approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
}