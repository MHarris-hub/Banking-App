package com.usfbank.app.model;

import java.util.Date;
import java.util.List;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Date dob;
    private List<Account> accounts;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Date getDob() {
        return dob;
    }

    public List<Account> getAccounts() { return accounts; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}