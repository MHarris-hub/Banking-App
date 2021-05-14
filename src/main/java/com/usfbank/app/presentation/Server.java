package com.usfbank.app.presentation;

import com.usfbank.app.exception.AccountException;
import com.usfbank.app.model.Customer;
import com.usfbank.app.model.Employee;
import com.usfbank.app.model.Transaction;
import com.usfbank.app.service.AccountManagementService;
import com.usfbank.app.service.impl.AccountManagementServiceImpl;
import io.javalin.Javalin;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

import org.json.JSONObject;

public class Server {
    public void start() {
        AccountManagementService accountManagement = new AccountManagementServiceImpl();
        Javalin app = Javalin.create(config->config.enableCorsForAllOrigins()).start(9000);

        //login (method is post so that username/password are not displayed in url)
        app.post("/customerlogin", ctx -> {
            Customer customer = ctx.bodyAsClass(Customer.class);

            if (accountManagement.login(customer.getUsername(), customer.getPassword(), "customer"))
                ctx.json(customer);
            else
                ctx.json("authentication failure");
       });

       //login (method is post so that username/password are not displayed in url)
       app.post("/employeelogin", ctx -> {
           Employee employee = ctx.bodyAsClass(Employee.class);

           if (accountManagement.login(employee.getUsername(), employee.getPassword(), "employee"))
               ctx.json(employee);
           else
               ctx.json("authentication failure");
       });

        //transaction search by id
        app.get("/employee-dash/:accountid/", ctx -> {
            List<Transaction> transactions;
            int accountid = Integer.parseInt(ctx.pathParam("accountid"));

            transactions = accountManagement.getTransactionLogByID(accountid);

            ctx.json(transactions);
        });

        //get balance
        app.get("/customer-dash/:accountid", ctx -> {
            BigDecimal balance = accountManagement.getBalance(Integer.parseInt(ctx.pathParam("accountid")));

            ctx.json(NumberFormat.getCurrencyInstance().format(balance));
        });

        //customer dashboard functionality (deposit, withdraw, transfer)
        app.post("/customer-dash/", ctx -> {
            JSONObject json = new JSONObject(ctx.body());
            String message = "";

            if (json.get("transactiontype").toString().equals("deposit")) {
                message = "Deposit successful";

                try {
                    int accountID = Integer.parseInt(json.get("accountid").toString());
                    BigDecimal amount = new BigDecimal(json.get("amount").toString());

                    try {
                        accountManagement.deposit(accountID, amount);
                    } catch (AccountException e) {
                        message = "Deposit failed";
                    }
                } catch (NumberFormatException e) {
                    message = "Enter a valid account ID or deposit amount";
                }
            } else if (json.get("transactiontype").toString().equals("withdrawal")) {
                message = "Withdrawal successful";

                try {
                    int accountID = Integer.parseInt(json.get("accountid").toString());
                    BigDecimal amount = new BigDecimal(json.get("amount").toString());

                    try {
                        accountManagement.withdraw(accountID, amount);
                    } catch (AccountException e) {
                        message = "Withdrawal failed";
                    }
                } catch (NumberFormatException e) {
                    message = "Enter a valid account ID or deposit amount";
                }
            } else if (json.get("transactiontype").toString().equals("transfer")) {
                message = "Transfer successful";

                try {
                    int fromAccountID = Integer.parseInt(json.get("fromaccountid").toString());
                    int toAccountID = Integer.parseInt(json.get("toaccountid").toString());
                    BigDecimal amount = new BigDecimal(json.get("amount").toString());

                    try {
                        accountManagement.transfer(fromAccountID, toAccountID, amount);
                    } catch (AccountException e) {
                        message = "Transfer failed";
                    }
                } catch (NumberFormatException e) {
                    message = "Enter a valid account ID or deposit amount";
                }
            }

            ctx.json(message);
        });
    }
}