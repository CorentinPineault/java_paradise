package com.formation.app;

import com.formation.app.dao.jdbc.JdbcPlaceDao;
import com.formation.app.util.ConnectionManager;

import java.sql.Connection;

public class Launcher {

    public static void main(String[] args) {
        Connection connection = ConnectionManager.getConnection();

        Place testPlace = new Place();
        testPlace.setName("pee");

    }
}
