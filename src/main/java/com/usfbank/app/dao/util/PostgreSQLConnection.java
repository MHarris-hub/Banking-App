package com.usfbank.app.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLConnection {
    private static Connection connection;

    //private constructor to enforce class as singleton
    private PostgreSQLConnection() {}

    //connect to database
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://usf-bank-records.cxugpskaec4k.us-east-2.rds.amazonaws.com/postgres";
        String username = "postgres";
        String password = "postgres";

        connection = DriverManager.getConnection(url, username, password);

        return connection;
    }
}