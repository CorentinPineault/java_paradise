package com.formation.app.dao.jdbc;

import com.formation.app.Place;
import com.formation.app.dao.PlaceDao;

import java.util.List;

public class JdbcPlaceDao extends JdbcDao implements PlaceDao{
    @Override
    public Long createPlace(Place place) {
        return null;
    }

    @Override
    public Place findPlaceById(Long id) {
        return null;
    }

    @Override
    public boolean updatePlace(Place place) {
        return false;
    }

    @Override
    public boolean removePlace(Place place) {
        return false;
    }

    @Override
    public List<Place> findAllPlace() {
        return null;
    }
}
