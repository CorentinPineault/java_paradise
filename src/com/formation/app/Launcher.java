package com.formation.app;

import com.formation.app.dao.DaoFactory;
import com.formation.app.dao.PlaceDao;
import com.formation.app.dao.jdbc.JdbcDao;
import com.formation.app.dao.jdbc.JdbcPlaceDao;
import com.formation.app.dao.jdbc.JdbcTripDao;
import com.formation.app.util.ConnectionManager;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Launcher {

    public static void main(String[] args) {
        JdbcPlaceDao placeDao = DaoFactory.GetPlaceDao();
        JdbcTripDao tripDao = DaoFactory.GetTripDao();

        Scanner scanner = new Scanner(System.in);
        
        /*
        List<Trip> listTrip = tripDao.findAllTrip();

        for (int i = 0; i < listTrip.size(); i++){
            System.out.println(listTrip.get(i).getPrix());
        }

        Place tempPlace = placeDao.findPlaceById(4L);
        Trip tempTrip = tripDao.findTripById(1L);
        tempPlace.setName("Visite picole");
        tempTrip.setPrix(9.99f);

        boolean testUpdatePlace = placeDao.updatePlace(tempPlace);
        boolean testUpdateTrip = tripDao.updateTrip(tempTrip);

        System.out.println(testUpdatePlace);
        System.out.println(testUpdateTrip);
        */
    }
}
