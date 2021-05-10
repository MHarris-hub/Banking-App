package com.usfbank.app.service.impl;

import com.usfbank.app.dao.EmployeeActionsDAO;
import com.usfbank.app.dao.impl.EmployeeActionsDAOImpl;
import com.usfbank.app.model.Account;
import com.usfbank.app.service.EmployeeActionsService;

import java.util.List;

public class EmployeeActionsServiceImpl implements EmployeeActionsService {
    EmployeeActionsDAO employeeActions = new EmployeeActionsDAOImpl();

    @Override
    public void setApproval(int accountID, boolean approvalStatus) {
        employeeActions.setApproval(accountID, approvalStatus);
    }

    @Override
    public List<Account> getPendingAccounts() {
        return employeeActions.getPendingAccounts();
    }
}
