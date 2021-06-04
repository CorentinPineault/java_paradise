package com.formation.app;

import com.formation.app.util.ConnectionManager;

import java.sql.Connection;

public class Launcher {

    public static void main(String[] args) {
        Connection connection = ConnectionManager.getConnection();
    }
}
