package com.usfbank.app.presentation;

import com.usfbank.app.model.Customer;
import com.usfbank.app.model.Employee;
import com.usfbank.app.model.Transaction;
import com.usfbank.app.service.AccountManagementService;
import com.usfbank.app.service.impl.AccountManagementServiceImpl;
import io.javalin.Javalin;

import java.util.List;

public class Server {
    public static void main(String[] args) {
        AccountManagementService accountManagement = new AccountManagementServiceImpl();
        Javalin app = Javalin.create(config->config.enableCorsForAllOrigins()).start(9000);

        //login (method is post so that username/password are not displayed in url)
        app.post("/customerlogin", ctx -> {
            Customer customer = ctx.bodyAsClass(Customer.class);

            if (accountManagement.login(customer.getUsername(), customer.getPassword(), "customer")) {
                System.out.println("login success");

            } else {
                System.out.println("login failure");
            }

            ctx.json(customer);
       });

       //login (method is post so that username/password are not displayed in url)
       app.post("/employeelogin", ctx -> {
           Employee employee = ctx.bodyAsClass(Employee.class);

           if (accountManagement.login(employee.getUsername(), employee.getPassword(), "employee")) {
               System.out.println("login success");
           } else {
               System.out.println("login failure");
           }

           ctx.json(employee);
       });

        //transaction search by id
        app.get("/employee-dash/:accountid", ctx -> {
            List<Transaction> transactions;
            int accountid = Integer.parseInt(ctx.pathParam("accountid"));

            transactions = accountManagement.getTransactionLogByID(accountid);

            ctx.json(transactions);
        });

//        //view users transactions
//        //(username, password, accountID)
//        app.get("/transactions/*/*/*",ctx -> {
//            try{
//                user user = logic.login(ctx.splat(0), ctx.splat(1));
//                if (user == null) throw new BadLogin();
//                ctx.json(logic.transactionsFromAccount(user, Integer.parseInt(ctx.splat(2))));
//            }
//            catch (BadLogin e){
//                ctx.status(400);
//            }catch (NumberFormatException e){
//                ctx.status(400);
//            }catch (BusinessException e){
//                ctx.status(500);
//            }
//        });

        //service = accountManagement
//        app.post("/employee", ctx -> {
//            Employee employee = ctx.bodyAsClass(Employee.class);
//            employee = service.createEmployee(employee);
//            ctx.json(employee);
//        });
//
//        app.put("/employee", ctx -> {
//            Employee employee = ctx.bodyAsClass(Employee.class);
//            employee = service.updateEmployee(employee);
//            ctx.json(employee);
//        });
//
//        app.get("/employee/:id", ctx -> {
//            // Employee employee=ctx.bodyAsClass(Employee.class);
//            Employee employee = service.getEmployeeById(Integer.parseInt(ctx.pathParam("id")));
//            ctx.json(employee);
//        });
//
//        app.delete("/employee/:id", ctx -> {
//            // Employee employee=ctx.bodyAsClass(Employee.class);
//            int id = Integer.parseInt(ctx.pathParam("id"));
//            service.removeEmployee(id);
//            ctx.result("Employee with id " + id + " removed successfully");
//        });
//
//        app.get("/employees", ctx -> {
//            List<Employee> employeeList = service.getEmployeesList();
//            ctx.json(employeeList);
//        });
//
//        app.get("/employees/:age", ctx -> {
//            List<Employee> employeeList = service.getEmployeesByAge(Integer.parseInt(ctx.pathParam("age")));
//            ctx.json(employeeList);
//        });
    }
}