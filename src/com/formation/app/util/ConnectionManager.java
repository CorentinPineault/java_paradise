package com.formation.app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {
    private static final String url = "jdbc:mysql://localhost:8889/java_paradise?serverTimezone=UTC";
    private static final String user = "corentin";
    private static final String password = "ihatecoding";

    private static Connection connection;

    private ConnectionManager(){

    }
    public static Connection getConnection(){
        if (connection == null) {
            try {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    System.out.println("Driver introuvable");
                }
                connection = DriverManager.getConnection(url, user, password);
                connection.setAutoCommit(false);
            } catch (SQLException throwables) {
                throw new RuntimeException("Connection can't be created.");
            }
        }
        return connection;
    }


    public static void close(){
        try{
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}