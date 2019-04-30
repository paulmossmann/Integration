package com.daviddev.projetgocaching_intgration1;

import android.util.Log;

public class Course {

    static int[] geocacheIds;
    static int currentIndex;

    public static boolean isEnded(){
        if(currentIndex + 1 == geocacheIds.length){
            currentIndex = -1; // Remise de currentIndex à ça valeur de départ de parcours.
            return true;
        }
        else
            return false;
    }

    public static void geocacheHasBeenScanned(){
        currentIndex++;
    }

    public static int getCurrentGeocacheId(){
        return geocacheIds[currentIndex];
    }

    public static int getNextGeocacheId(){
        return geocacheIds[currentIndex+1];
    }

    public static void setupCourse(int courseId){
        geocacheIds = DataBaseManager.getAllCourse(courseId);
        currentIndex = -1; // Valeur de départ de parcours, s'incrémente avec  geocacheHasBeenScanned()
    }

    public static int[] getCoursesIds(){
        return DataBaseManager.getCoursesIds();
    }

}