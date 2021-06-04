package com.formation.app.dao;

import com.formation.app.dao.jdbc.JdbcPlaceDao;
import com.formation.app.dao.jdbc.JdbcTripDao;

public class DaoFactory {
    private DaoFactory() {
    }

    public static JdbcPlaceDao GetPlaceDao() {
        return new JdbcPlaceDao();
    }

    public static JdbcTripDao GetTripDao() {
        return new JdbcTripDao();
    }
}
