package com.usfbank.app.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class Transaction {
    private final int id;
    private final String type;
    private final BigDecimal amount;
    private final int toAccount;
    private final int fromAccount;
    private final Timestamp timestamp;

    public Transaction(int id, String type, BigDecimal amount, int toAccount, int fromAccount, Timestamp timestamp) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.toAccount = toAccount;
        this.fromAccount = fromAccount;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public int getToAccount() {
        return toAccount;
    }

    public int getFromAccount() {
        return fromAccount;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public static String printTransactions(List<Transaction> transactionList) {
        String resultList = "";

        for (Transaction transaction : transactionList)
            resultList +=
                transaction.getId() + ", " +
                transaction.getType() + ", " +
                transaction.getAmount() + ", " +
                transaction.getFromAccount() + ", " +
                transaction.getToAccount() + ", " +
                transaction.getTimestamp() + '\n';

        return resultList;
    }
}