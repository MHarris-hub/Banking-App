package com.usfbank.app.main;

import com.usfbank.app.model.Account;
import com.usfbank.app.model.Employee;
import com.usfbank.app.service.EmployeeActionsService;
import com.usfbank.app.service.impl.EmployeeActionsServiceImpl;
import com.usfbank.app.service.util.InputValidation;
import com.usfbank.app.exception.AccountException;
import com.usfbank.app.model.Transaction;
import com.usfbank.app.service.AccountManagementService;
import com.usfbank.app.service.impl.AccountManagementServiceImpl;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Logger logger = Logger.getLogger("console");

    public static void main (String[] args) {
        int selection = -1; //determine the selection for the main menu
        AccountManagementService accountManagement = new AccountManagementServiceImpl();

//-----------------TEST CODE---------------------

        //create new account
//        Account a = new Account();
//
//        a.setBalance(new BigDecimal(1000));
//        a.setApprovalStatus(false);

        //accountManagement.applyForAccount(a);

        //register new employee
        Employee emp = new Employee();
        emp.setUsername("emp1");
        emp.setPassword("pass1");
        
        //accountManagement.registerEmployee(emp);

        EmployeeActionsService empServ = new EmployeeActionsServiceImpl();

        //delete pending account
        //abc.setApproval(3, false);

        //approve account
        //abc.setApproval(3, true);

//        ArrayList<Account> accList = (ArrayList<Account>) empServ.getPendingAccounts();
//
//        logger.info("id" + accList.get(0).getId());
//        logger.info("balance" + accList.get(0).getBalance());

//--------------------END TEST CODE---------------

        logger.info("");
        logger.info("================================================================");
        logger.info("Welcome to the USF BSN CTS CDI Tenweek Bank Banking Application!");
        logger.info("================================================================");
        logger.info("");

        //display main menu
        do {
            logger.info("Please an option (0 - 3):");
            logger.info("1) Customer login");
            logger.info("2) Employee login");
            logger.info("3) Apply for a new account");
            logger.info("0) Exit");

            try {
                selection = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                logger.info("Invalid input: selection must be a number (0 - 3)");
            }

            int innerSelection = -1;    //determine user selection for inner switch branches

            switch(selection) {
                case 1:
                    String username;
                    String password;

                    logger.info("");
                    logger.info("===============================");
                    logger.info("========CUSTOMER LOGIN=========");
                    logger.info("===============================");
                    logger.info("");
                    do {
                        logger.info("Enter your username:");
                        logger.info("Usernames are 3-20 characters in length and can contain letters and numbers.");
                        username = scanner.nextLine();
                    } while (!InputValidation.isValidUsername(username));

                    do {
                        logger.info("Enter your password:");
                        logger.info("Passwords are 3-20 characters in length and can contain letters numbers and the characters: !#$%&()");
                        password = scanner.nextLine();
                    } while (!InputValidation.isValidPassword(password));

                    if (accountManagement.login(username, password, "customer")) {
                        logger.info("Login successful");
                        logger.info("");

                        //user account menu
                        do {
                            logger.info("Welcome " + username + ". Please select an option (0 - 4):");
                            logger.info("1) View balance");
                            logger.info("2) Make a deposit");
                            logger.info("3) Make a withdrawal");
                            logger.info("4) Transfer money to another account");
                            logger.info("0) Exit");

                            try {
                                innerSelection = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                logger.info("Invalid input: selection must be a number (0 - 4)");
                            }

                            int id;             //used to manage accounts in the following switch
                            int idTo;           //destination account for money transfers in the following switch
                            BigDecimal amount;  //used to manage balances and funds being moved in the following switch

                            switch(innerSelection) {
                                case 1:
                                    try {
                                        logger.info("Enter account id:");
                                        amount = accountManagement.getBalance(Integer.parseInt(scanner.nextLine()));

                                        logger.info("Current balance is: " + NumberFormat.getCurrencyInstance().format(amount));
                                        logger.info("");
                                    } catch (NumberFormatException e) {
                                        logger.info("Invalid input");
                                    }
                                    break;
                                case 2:
                                    try {
                                        logger.info("Enter account id:");
                                        id = Integer.parseInt(scanner.nextLine());

                                        logger.info("Enter amount to deposit:");
                                        amount = BigDecimal.valueOf(Double.parseDouble(scanner.nextLine()));

                                        try {
                                            accountManagement.deposit(id, amount);
                                        } catch (AccountException e) {
                                            logger.info("Invalid account number or amount.");
                                        }
                                    } catch (NumberFormatException e) {
                                        logger.info("Invalid input");
                                    }
                                    break;
                                case 3:
                                    try {
                                        logger.info("Enter account id:");
                                        id = Integer.parseInt(scanner.nextLine());

                                        logger.info("Enter amount to withdraw:");
                                        amount = BigDecimal.valueOf(Double.parseDouble(scanner.nextLine()));

                                        try {
                                            accountManagement.withdraw(id, amount);
                                        } catch (AccountException e) {
                                            logger.info("Invalid account number or amount.");
                                        }
                                    } catch (NumberFormatException e) {
                                        logger.info("Invalid input");
                                    }
                                    break;
                                case 4:
                                    try {
                                        logger.info("Enter origin account id:");
                                        id = Integer.parseInt(scanner.nextLine());

                                        logger.info("Enter destination account id:");
                                        idTo = Integer.parseInt(scanner.nextLine());

                                        logger.info("Enter amount to transfer:");
                                        amount = BigDecimal.valueOf(Double.parseDouble(scanner.nextLine()));

                                        try {
                                            accountManagement.transfer(id, idTo, amount);
                                        } catch (AccountException e) {
                                            logger.info("Invalid account number or amount.");
                                        }
                                    } catch (NumberFormatException e) {
                                        logger.info("Invalid input");
                                    }
                                    break;
                            }
                        } while(innerSelection != 0);
                    } else
                        logger.info("Login unsuccessful");
                    break;
                case 2:
                    logger.info("");
                    logger.info("===============================");
                    logger.info("========EMPLOYEE LOGIN=========");
                    logger.info("===============================");
                    logger.info("");

                    //employee account menu
                    do {
                        logger.info("1) Search transactions by origin account id");
                        logger.info("2) Search transactions by type");
                        logger.info("3) Search transactions by name");
                        logger.info("4) Approve/deny accounts");
                        logger.info("0) Exit");
                        logger.info("");

                        innerSelection = Integer.parseInt(scanner.nextLine());

                        switch(innerSelection) {
                            case 1:
                                logger.info("Enter the origin account number to search for:");
                                int id = Integer.parseInt(scanner.nextLine());

                                //Transaction.printTransactions(accountManagement.getTransactionLog(id));
                                logger.info(Transaction.printTransactions(accountManagement.getTransactionLog(id)));
                                break;
                        }

                    } while(innerSelection != 0);

                    break;
                case 3:
                    logger.info("");
                    logger.info("===============================");
                    logger.info("=====APPLY FOR NEW ACCOUNT=====");
                    logger.info("===============================");
                    logger.info("");
                    break;
            }

        } while(selection != 0);

        logger.info("");
        logger.info("=========================================================================");
        logger.info("Thank you for using the USF BSN CTS CDI Tenweek Bank Banking Application!");
        logger.info("=========================================================================");
        logger.info("");
    }
}