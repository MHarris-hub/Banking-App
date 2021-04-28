package com.usfbank.app.dao;

import com.usfbank.app.exception.AccountException;
import com.usfbank.app.model.Account;
import com.usfbank.app.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface AccountManagementDAO {
    public boolean login(String username, String password);
    public BigDecimal getBalance(int accountID);
    public void applyForAccount(Account account);
    public void deposit(int accountID, BigDecimal amount) throws AccountException;
    public void withdraw(int accountID, BigDecimal amount) throws AccountException;
    public void transfer(int fromAccountID, int toAccountID, BigDecimal amount) throws AccountException;
    public List<Transaction> getTransactionLog(int accountID);
}