package com.formation.app.dao;

import com.formation.app.Place;

import java.util.List;

public interface PlaceDao<T> {
    Long createPlace(T obj);

    T findPlaceById(Long id);

    boolean updatePlace(T obj);

    boolean removePlace(T obj);

    List<T> findAllPlace();
}
