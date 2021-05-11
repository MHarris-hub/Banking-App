package com.usfbank.app.service;

import com.usfbank.app.exception.AccountException;
import com.usfbank.app.model.Account;
import com.usfbank.app.model.Employee;
import com.usfbank.app.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface AccountManagementService {
    boolean login(String username, String password, String userType);
    BigDecimal getBalance(int accountID);
    void registerEmployee(Employee employee);
    void applyForAccount(Account account);
    void deposit(int accountID, BigDecimal amount) throws AccountException;
    void withdraw(int accountID, BigDecimal amount) throws AccountException;
    void transfer(int fromAccountID, int toAccountID, BigDecimal amount) throws AccountException;
    List<Transaction> getTransactionLogByID(int accountID);
}