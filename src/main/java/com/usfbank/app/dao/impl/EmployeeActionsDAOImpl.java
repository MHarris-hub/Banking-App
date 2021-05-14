package com.usfbank.app.dao.impl;

import com.usfbank.app.dao.EmployeeActionsDAO;
import com.usfbank.app.dao.util.PostgreSQLConnection;
import com.usfbank.app.model.Account;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//beginning to refactor by moving relevant methods here from AccountManagementDAOImpl/AccountManagementDAO to reduce file length
public class EmployeeActionsDAOImpl implements EmployeeActionsDAO {
    static Logger fileLogger = Logger.getLogger("file");
    static final String LOG_MESSAGE = "ClassNotFoundException or SQLException occurred";

    @Override
    public void setApproval(int accountID, boolean approvalStatus) {
            PreparedStatement preparedStatement;

            //if the account is approved, change the status, otherwise delete the account entry from the database
            try (Connection connection = PostgreSQLConnection.getConnection()) {
                String sql;

                if (approvalStatus) {
                    sql = "UPDATE bank_records.accounts SET approval_status = ? WHERE id = ?";

                    preparedStatement = connection.prepareStatement(sql);

                    preparedStatement.setBoolean(1, true);
                    preparedStatement.setInt(2, accountID);

                } else {
                    sql = "DELETE FROM bank_records.accounts WHERE id = ?";

                    preparedStatement = connection.prepareStatement(sql);

                    preparedStatement.setInt(1, accountID);

                }
                preparedStatement.executeUpdate();

            } catch (ClassNotFoundException | SQLException e) {
                fileLogger.error(LOG_MESSAGE);
            }
    }

    @Override
    public List<Account> getPendingAccounts() {
        PreparedStatement preparedStatement;
        List<Account> accountList = new ArrayList<>();
        ResultSet resultSet;

        try (Connection connection = PostgreSQLConnection.getConnection()) {
            String sql = "SELECT id, balance FROM bank_records.accounts WHERE approval_status = false";

            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                accountList.add(new Account(
                        resultSet.getInt("id"),
                        resultSet.getBigDecimal("balance"), false)
                );
            }

        } catch (ClassNotFoundException | SQLException e) {
            fileLogger.error(LOG_MESSAGE);
        }

        return accountList;
    }
}