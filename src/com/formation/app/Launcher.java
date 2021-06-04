package com.formation.app;

import com.formation.app.dao.DaoFactory;
import com.formation.app.dao.jdbc.JdbcPlaceDao;
import com.formation.app.dao.jdbc.JdbcTripDao;
import com.formation.app.util.ConnectionManager;

import java.util.Scanner;

public class Launcher {

    public static void main(String[] args) {
        JdbcPlaceDao placeDao = DaoFactory.GetPlaceDao();
        JdbcTripDao tripDao = DaoFactory.GetTripDao();
        Place place;
        Trip trip;

        Scanner scanner = new Scanner(System.in);

        String input;

        do {
            System.out.println("What do you want to do ?");
            System.out.println("1 - Add a place");
            System.out.println("2 - Find a place");
            System.out.println("3 - Edit a place");
            System.out.println("4 - Remove a place");
            System.out.println("5 - Add a trip");
            System.out.println("6 - Find a trip");
            System.out.println("7 - Remove a trip");
            System.out.println("8 - Quit");
            System.out.println();

            input = scanner.nextLine();

            switch (input) {
                case "1":
                    try {
                        System.out.print("Name : ");
                        input = scanner.nextLine();

                        place = new Place();
                        place.setName(input);

                        System.out.println("Place added with id " + placeDao.createPlace(place));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;

                case "2":
                    try {
                        System.out.print("Id : ");
                        input = scanner.nextLine();

                        place = placeDao.findPlaceById(Long.parseLong(input));

                        System.out.println("Place found : " + place.getName());
                    } catch (Exception e) {
                        System.err.println("Place not found");
                        System.out.println();
                    }
                    break;

                case "3":
                    try {
                        System.out.print("Id : ");
                        input = scanner.nextLine();

                        place = placeDao.findPlaceById(Long.parseLong(input));
                        System.out.println("Place : " + place.getName());
                        System.out.println();

                        System.out.print("New name : ");
                        input = scanner.nextLine();
                        place.setName(input);

                        placeDao.updatePlace(place);
                        System.out.println("Name changed");

                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case "4":
                    try {
                        System.out.print("Id : ");
                        input = scanner.nextLine();

                        place = placeDao.findPlaceById(Long.parseLong(input));
                        System.out.println("Place : " + place.getName());

                        System.out.println("All trips with this place will be deleted as well.");
                        System.out.println("Are you sure you want to remove it ? [yes/no]");
                        input = scanner.nextLine();

                        if (input.equals("yes")) {
                            placeDao.removePlace(place);
                            System.out.println("Place deleted !");
                        }
                        else{
                            if (input.equals("no")){
                                System.out.println("Cancelling deletion");
                            } else{
                                System.out.println("Input unknown: cancelling deletion");
                            }
                        }

                        System.out.println();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case "5":
                    try {
                        trip = new Trip();

                        System.out.print("Departure : please enter the id of the place : ");
                        input = scanner.nextLine();

                        trip.setLieuDepart(Long.parseLong(input));

                        System.out.print("Destination : please enter the id of the place : ");
                        input = scanner.nextLine();

                        trip.setLieuArrivee(Long.parseLong(input));

                        System.out.print("Price : ");
                        input = scanner.nextLine();

                        trip.setPrix(Float.parseFloat(input));

                        System.out.println("Trip added with id " + tripDao.createTrip(trip));
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }

                    break;

                case "6":
                    try {
                        System.out.print("Id : ");
                        input = scanner.nextLine();

                        trip = tripDao.findTripById(Long.parseLong(input));

                        System.out.println("Trip found : "
                                + placeDao.findPlaceById(trip.getLieuDepart()).getName()
                                + " - "
                                + placeDao.findPlaceById(trip.getLieuArrivee()).getName()
                                + " for " + trip.getPrix());
                    }
                    catch (Exception e){
                        System.err.println("Trip not found");
                    }
                    break;

                case "7":
                    try {
                        System.out.print("Id : ");
                        input = scanner.nextLine();

                        trip = tripDao.findTripById(Long.parseLong(input));
                        System.out.println("Trip : "
                                + placeDao.findPlaceById(trip.getLieuDepart()).getName()
                                + " - "
                                + placeDao.findPlaceById(trip.getLieuArrivee()).getName()
                                + " for " + trip.getPrix());

                        System.out.println("Are you sure you want to remove it ? [yes/no]");
                        input = scanner.nextLine();

                        if (input.equals("yes")) {
                            tripDao.removeTrip(trip);
                            System.out.println("Trip deleted !");
                        }
                        else{
                            if (input.equals("no")){
                                System.out.println("Cancelling deletion");
                            } else{
                                System.out.println("Input unknown: cancelling deletion");
                            }
                        }

                        System.out.println();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case "8":
                    System.out.println("Do you really want to quit ? (retype 8 to confirm)");
                    input = scanner.nextLine();
                    break;

                default:
                    System.out.println("Wrong input: try again");
            }
            System.out.println();

        } while (!input.equals("8"));

        ConnectionManager.close();
    }
}
