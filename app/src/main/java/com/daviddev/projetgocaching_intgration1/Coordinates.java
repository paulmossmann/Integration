package com.daviddev.projetgocaching_intgration1;

public class Coordinates {

    long latitude;
    long longitude;

    public Coordinates( long p_latitude, long p_longitude){
        latitude = p_latitude;
        longitude = p_longitude;



    }

    long getLatitude(){
        return latitude;
    }

    long getlongitude(){
        return longitude;
    }

}
