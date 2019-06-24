package com.example.getrequestexample.utils;

//Класс для расчета расстояния между двумя точками по широте и долготе в км в
public class DistanceCalculator {

    public  static double getDistance(double userLatitude, double userLongitude, double shopLatitude, double shopLongitude){
        if ((userLatitude == shopLatitude) && (userLongitude == shopLongitude)) {
            return 0;
        }
        double theta = userLongitude - shopLongitude;
        double dist = Math.sin(Math.toRadians(userLatitude)) * Math.sin(Math.toRadians(shopLatitude)) +
                Math.cos(Math.toRadians(userLatitude)) * Math.cos(Math.toRadians(shopLatitude)) * Math.cos(Math.toRadians(theta));
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;

        return dist;
    }
}
