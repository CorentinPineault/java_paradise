package com.formation.app.dao;

import com.formation.app.Place;

import java.util.List;

public interface PlaceDao {
    Long createPlace(Place place);

    Place findPlaceById(Long id);

    boolean updatePlace(Place place);

    boolean removePlace(Place place);

    List<Place> findAllPlace();
}
