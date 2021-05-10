package com.usfbank.app.dao;

import com.usfbank.app.model.Account;

import java.util.List;

public interface EmployeeActionsDAO {
    void setApproval(int accountID, boolean approvalStatus);
    List<Account> getPendingAccounts();
}