package com.formation.app.dao;

import com.formation.app.Trip;

import java.util.List;

public interface TripDao<T> {
    Long createTrip(T obj);

    T findTripById(Long id);

    boolean updateTrip(T obj);

    boolean removeTrip(T objp);

    List<T> findAllTrip();
}
