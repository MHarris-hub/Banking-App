package com.usfbank.app.service.impl;

import com.usfbank.app.dao.AccountManagementDAO;
import com.usfbank.app.dao.impl.AccountManagementDAOImpl;
import com.usfbank.app.exception.AccountException;
import com.usfbank.app.model.Account;
import com.usfbank.app.model.Transaction;
import com.usfbank.app.service.AccountManagementService;

import java.math.BigDecimal;
import java.util.List;

public class AccountManagementServiceImpl implements AccountManagementService {

    AccountManagementDAO accountManagement = new AccountManagementDAOImpl();

    @Override
    public boolean login(String username, String password) {
        return accountManagement.login(username, password);
    }

    @Override
    public BigDecimal getBalance(int accountID) {
        return accountManagement.getBalance(accountID);
    }

    @Override
    public void applyForAccount(Account account) {
        accountManagement.applyForAccount(account);
    }

    @Override
    public void deposit(int accountID, BigDecimal amount) throws AccountException {
        accountManagement.deposit(accountID, amount);
    }

    @Override
    public void withdraw(int accountID, BigDecimal amount) throws AccountException {
        accountManagement.withdraw(accountID, amount);
    }

    @Override
    public void transfer(int fromAccountID, int toAccountID, BigDecimal amount) throws AccountException {
        accountManagement.transfer(fromAccountID, toAccountID, amount);
    }

    @Override
    public List<Transaction> getTransactionLog(int accountID) {
        return accountManagement.getTransactionLog(accountID);
    }
}