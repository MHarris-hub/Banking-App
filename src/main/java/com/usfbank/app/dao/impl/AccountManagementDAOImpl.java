package com.usfbank.app.dao.impl;

import com.usfbank.app.InputValidation;
import com.usfbank.app.dao.AccountManagementDAO;
import com.usfbank.app.dao.util.PostgreSQLConnection;
import com.usfbank.app.exception.AccountException;
import com.usfbank.app.model.Account;
import com.usfbank.app.model.Transaction;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class AccountManagementDAOImpl implements AccountManagementDAO {

    static Logger logger = Logger.getLogger(AccountManagementDAOImpl.class);

    @Override
    public boolean login(String username, String password) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sql = "select username, password from bank_records.customers where username = ? and password = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String dbUsername = resultSet.getString("username");
                String dbPassword = resultSet.getString("password");

                if (dbUsername.equals(username) && dbPassword.equals(password))
                    return true;
            }

        } catch (ClassNotFoundException | SQLException e) {
            logger.info("Database error occurred.");
        }

        return false;
    }

    @Override
    public BigDecimal getBalance(int accountID) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        BigDecimal balance  = new BigDecimal(0);

        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sql = "select balance from bank_records.accounts where id = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountID);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
                balance = resultSet.getBigDecimal("balance");

        } catch (ClassNotFoundException | SQLException e) {
            logger.info("Database error occurred.");
        }

        return balance;
    }

    @Override
    public void applyForAccount(Account account) {
        //to be implemented
    }

    @Override
    public void deposit(int accountID, BigDecimal amount) throws AccountException {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        BigDecimal balance = new BigDecimal(0);

        if (!InputValidation.isValidAmount(amount))
            throw new AccountException();

        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sql = "select balance from bank_records.accounts where id = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountID);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                balance = resultSet.getBigDecimal("balance");
                balance = balance.add(amount);
            }

            sql = "update bank_records.accounts set balance = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBigDecimal(1, balance);
            preparedStatement.setInt(2, accountID);
            preparedStatement.executeUpdate();

            generateLog("deposit", amount, -1, accountID);
        } catch (ClassNotFoundException | SQLException e) {
            logger.info("Database error occurred.");
        }


    }

    @Override
    public void withdraw(int accountID, BigDecimal amount) throws AccountException {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        BigDecimal balance = new BigDecimal(0);

        if (!InputValidation.isValidAmount(amount))
            throw new AccountException();

        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sql = "select balance from bank_records.accounts where id = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountID);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                balance = resultSet.getBigDecimal("balance");
                balance = balance.subtract(amount);
            }

            if (balance.compareTo(new BigDecimal(0)) == -1)
                throw new AccountException();

            sql = "update bank_records.accounts set balance = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBigDecimal(1, balance);
            preparedStatement.setInt(2, accountID);
            preparedStatement.executeUpdate();

            generateLog("withdrawal", amount, accountID, -1);
        } catch (ClassNotFoundException | SQLException e) {
            logger.info("Database error occurred.");
        }
    }

    @Override
    public void transfer(int fromAccountID, int toAccountID, BigDecimal amount) throws AccountException {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        BigDecimal balance = new BigDecimal(0);

        if (!InputValidation.isValidAmount(amount))
            throw new AccountException();

        //withdraw from fromAccount
        //if withdraw throws an AccountException, money is not deposited into other account
        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sql = "select balance from bank_records.accounts where id = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, fromAccountID);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                balance = resultSet.getBigDecimal("balance");
                balance = balance.subtract(amount);
            }

            if (balance.compareTo(new BigDecimal(0)) == -1)
                throw new AccountException();

            sql = "update bank_records.accounts set balance = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBigDecimal(1, balance);
            preparedStatement.setInt(2, fromAccountID);
            preparedStatement.executeUpdate();

            //deposit into toAccount
            sql = "select balance from bank_records.accounts where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, toAccountID);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                balance = resultSet.getBigDecimal("balance");
                balance = balance.add(amount);
            }

            sql = "update bank_records.accounts set balance = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBigDecimal(1, balance);
            preparedStatement.setInt(2, toAccountID);
            preparedStatement.executeUpdate();

            generateLog("transfer", amount, fromAccountID, toAccountID);
        } catch (ClassNotFoundException | SQLException e) {
            logger.info("Database error occurred.");
        }
    }

    //1) as more logging methods are added, they will all be moved to a separate logging class to reduce the size of
    // AccountManagementDAOImpl
    //2) need to validate that accounts numbers exist before logging a phantom transaction with their numbers;
    // maybe that should be done in the withdraw, deposit and transfer

    //commit transaction log to database
    public void generateLog(String type, BigDecimal amount, int fromAccountID, int toAccountID) {
        PreparedStatement preparedStatement;
        String sql = "insert into bank_records.transactions(transaction_type, amount, from_account, to_account, timestamp) values(?, ?, ?, ?, ?)";

        try (Connection connection = PostgreSQLConnection.getConnection()) {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, type);
            preparedStatement.setBigDecimal(2, amount);
            preparedStatement.setInt(3, fromAccountID);
            preparedStatement.setInt(4, toAccountID);
            preparedStatement.setTimestamp(5, Timestamp.from(Instant.now()));

            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            logger.info("Could not log transaction.");
        }
    }

    @Override
    public List<Transaction> getTransactionLog(int accountID) {
        PreparedStatement preparedStatement;
        List<Transaction> transactionList = new ArrayList<>();
        ResultSet resultSet;

        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sql = "select id, transaction_type, amount, from_account, to_account, timestamp from bank_records.transactions where from_account = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountID);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                transactionList.add(new Transaction(
                    resultSet.getInt("id"),
                    resultSet.getString("transaction_type"),
                    resultSet.getBigDecimal("amount"),
                    resultSet.getInt("from_account"),
                    resultSet.getInt("to_account"),
                    resultSet.getTimestamp("timestamp"))
                );
            }

        } catch (ClassNotFoundException | SQLException e) {
            logger.info("Database error occurred.");
        }

        return transactionList;
    }
}