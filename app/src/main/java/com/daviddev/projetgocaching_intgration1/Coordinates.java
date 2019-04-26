package com.daviddev.projetgocaching_intgration1;

public class Coordinates {

    private double latitude;
    private double longitude;

    public Coordinates(double p_latitude,double p_longitude){
        latitude = p_latitude;
        longitude = p_longitude;
    }

    double getLatitude(){
        return latitude;
    }

    double getlongitude(){
        return longitude;
    }

}
