package com.formation.app.dao;

import com.formation.app.Trip;

import java.util.List;

public interface TripDao {
    Long createTrip(Trip trip);

    Trip findTripById(Long id);

    boolean updateTrip(Trip trip);

    boolean removeTrip(Trip trip);

    List<Trip> findAllTrip();
}
