package com.formation.app.dao.jdbc;

import com.formation.app.Place;
import com.formation.app.dao.PlaceDao;
import com.formation.app.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcPlaceDao extends JdbcDao implements PlaceDao<Place>{
    @Override
    public Long createPlace(Place place) {
        Place createdPlace = null;
        String query = "INSERT INTO Place(name) VALUES(?)";
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, place.getName());
            pst.execute();

            // Fetching generated id from database during insert
            ResultSet resultSet = pst.getGeneratedKeys();
            resultSet.next();
            Long id = resultSet.getLong(1);

            createdPlace = findPlaceById(id);

            connection.commit();

        } catch (SQLException e1) {
            e1.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return createdPlace.getId();
    }

    @Override
    public Place findPlaceById(Long id) {
        String query = "SELECT * FROM Place WHERE id=?";
        Connection connection = ConnectionManager.getConnection();
        Place foundPlace = null;

        try (PreparedStatement pst = ConnectionManager.getConnection().prepareStatement(query)) {
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                foundPlace = new Place();
                foundPlace.setId(rs.getLong("Id"));
                foundPlace.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundPlace;
    }


    @Override
    public boolean updatePlace(Place place) {
        boolean success = false;
        String query = "UPDATE Place SET name = ? WHERE id = ?";
        Connection connection = ConnectionManager.getConnection();

        try (PreparedStatement pst = ConnectionManager.getConnection().prepareStatement(query)) {
            pst.setString(1, place.getName());
            pst.setLong(2, place.getId());
            success = pst.executeUpdate() > 0;

            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean removePlace(Place place) {
        boolean success = false;
        String query = "DELETE FROM Place WHERE id=?";
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement pst = ConnectionManager.getConnection().prepareStatement(query)) {
            pst.setLong(1, place.getId());
            success = pst.executeUpdate() > 0;

            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public List<Place> findAllPlace() {
        List<Place> listPlaces = new ArrayList<Place>();
        Place tempPlace = null;
        String query = "SELECT * FROM Place";
        Connection connection = ConnectionManager.getConnection();

        try (PreparedStatement pst = ConnectionManager.getConnection().prepareStatement(query)) {
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                tempPlace = new Place();
                tempPlace.setId(rs.getLong("Id"));
                tempPlace.setName(rs.getString("name"));

                listPlaces.add(tempPlace);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listPlaces;

    }
}
