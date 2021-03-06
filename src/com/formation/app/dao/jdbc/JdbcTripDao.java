package com.formation.app.dao.jdbc;

import com.formation.app.Trip;
import com.formation.app.dao.TripDao;
import com.formation.app.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcTripDao extends JdbcDao implements TripDao<Trip> {
    @Override
    public Long createTrip(Trip trip) {
        Long id = null;
        String query = "INSERT INTO Trip(lieuDepart, lieuArrivee, prix) VALUES(?, ?, ?)";
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pst.setLong(1, trip.getLieuDepart());
            pst.setLong(2, trip.getLieuArrivee());
            pst.setFloat(3, trip.getPrix());
            pst.execute();

            // Fetching generated id from database during insert
            ResultSet resultSet = pst.getGeneratedKeys();
            resultSet.next();
            id = resultSet.getLong(1);

            connection.commit();

        } catch (SQLException e1) {
            e1.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return id;
    }

    @Override
    public Trip findTripById(Long id) {
        String query = "SELECT * FROM Trip WHERE id=?";
        Trip foundTrip = null;

        try (PreparedStatement pst = ConnectionManager.getConnection().prepareStatement(query)) {
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                foundTrip = new Trip();
                foundTrip.setId(rs.getLong("Id"));
                foundTrip.setLieuDepart(rs.getLong("lieuDepart"));
                foundTrip.setLieuArrivee(rs.getLong("lieuArrivee"));
                foundTrip.setPrix(rs.getFloat("prix"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundTrip;
    }

    @Override
    public boolean updateTrip(Trip trip) {
        boolean success = false;
        String query = "UPDATE Trip SET lieuDepart = ?, lieuArrivee = ?, prix = ? WHERE id=?";
        Connection connection = ConnectionManager.getConnection();

        try (PreparedStatement pst = ConnectionManager.getConnection().prepareStatement(query)) {
            pst.setLong(1, trip.getLieuDepart());
            pst.setLong(2, trip.getLieuArrivee());
            pst.setFloat(3, trip.getPrix());
            pst.setLong(4, trip.getId());
            success = pst.executeUpdate() > 0;

            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean removeTrip(Trip trip) {
        boolean success = false;
        String query = "DELETE FROM Trip WHERE id=?";
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement pst = ConnectionManager.getConnection().prepareStatement(query)) {
            pst.setLong(1, trip.getId());
            success = pst.executeUpdate() > 0;

            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public List<Trip> findAllTrip() {
        List<Trip> listTrips = new ArrayList<>();
        Trip tempTrip;
        String query = "SELECT * FROM Trip";

        try (PreparedStatement pst = ConnectionManager.getConnection().prepareStatement(query)) {
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                tempTrip = new Trip();
                tempTrip.setId(rs.getLong("Id"));
                tempTrip.setLieuDepart(rs.getLong("lieuDepart"));
                tempTrip.setLieuArrivee(rs.getLong("lieuArrivee"));
                tempTrip.setPrix(rs.getFloat("prix"));
                listTrips.add(tempTrip);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listTrips;
    }
}
