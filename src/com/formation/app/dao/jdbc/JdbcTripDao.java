package com.formation.app.dao.jdbc;

import com.formation.app.Trip;
import com.formation.app.dao.TripDao;

import java.util.List;

public class JdbcTripDao extends JdbcDao implements TripDao {
    @Override
    public Long createTrip(Trip trip) {
        return null;
    }

    @Override
    public Trip findTripById(Long id) {
        return null;
    }

    @Override
    public boolean updateTrip(Trip trip) {
        return false;
    }

    @Override
    public boolean removeTrip(Trip trip) {
        return false;
    }

    @Override
    public List<Trip> findAllTrip() {
        return null;
    }
}
