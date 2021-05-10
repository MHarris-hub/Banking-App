package com.usfbank.app.service;

import com.usfbank.app.model.Account;

import java.util.List;
public interface EmployeeActionsService {
    void setApproval(int accountID, boolean approvalStatus);
    List<Account> getPendingAccounts();
}