package com.usfbank.app.service.impl;

import com.usfbank.app.dao.AccountManagementDAO;
import com.usfbank.app.dao.impl.AccountManagementDAOImpl;
import com.usfbank.app.exception.AccountException;
import com.usfbank.app.model.Account;
import com.usfbank.app.model.Employee;
import com.usfbank.app.model.Transaction;
import com.usfbank.app.service.AccountManagementService;

import org.apache.log4j.Logger;
import java.math.BigDecimal;
import java.util.List;

public class AccountManagementServiceImpl implements AccountManagementService {
    static Logger fileLogger = Logger.getLogger("file");
    AccountManagementDAO accountManagement = new AccountManagementDAOImpl();

    @Override
    public boolean login(String username, String password, String userType) {
        if (accountManagement.login(username, password, userType))
            fileLogger.info("Login success for user: " + username);
        else
            fileLogger.info("Login failure for user: " + username);

        return accountManagement.login(username, password, userType);
    }

    @Override
    public BigDecimal getBalance(int accountID) {
        return accountManagement.getBalance(accountID);
    }

    @Override
    public void registerEmployee(Employee employee) {
        accountManagement.registerEmployee(employee);
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
    public List<Transaction> getTransactionLogByID(int accountID) {
        return accountManagement.getTransactionLogByID(accountID);
    }
}