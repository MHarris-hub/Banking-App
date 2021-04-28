package com.usfbank.app.model;

import org.apache.log4j.Logger;

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

    static Logger logger = Logger.getLogger(Transaction.class);

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

    public static void printTransactions(List<Transaction> transactionList) {

        for (Transaction transaction : transactionList)
            logger.info(transaction.getId() + "\t\t\t" +
                    transaction.getType() + "\t\t\t" +
                    transaction.getAmount() + "\t\t\t" +
                    transaction.getFromAccount() + "\t\t\t" +
                    transaction.getToAccount() + "\t\t\t" +
                    transaction.getTimestamp()
            );

        logger.info("");
    }
}