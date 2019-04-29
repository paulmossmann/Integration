package com.daviddev.projetgocaching_intgration1;

public class course {

    private static int Geocache1;
    private static int Geocache2;
    private static int Geocache3;

    public static int[] AllCourse;
    public static int IndexTab;

    public static int getGeocache1() {
        return Geocache1;
    }

    public static int getGeocache2() {
        return Geocache2;
    }

    public static int getGeocache3() {
        return Geocache3;
    }

    public static void setGeocache1() {
        Geocache1 = AllCourse[0];
    }

    public static void setGeocache2() {
        Geocache2 = AllCourse[1];
    }

    public static void setGeocache3() {
        Geocache3 = AllCourse[2];
    }
    
    public static void setAllCourse(int IdCourse) {
        AllCourse = DataBaseManager.getAllCourse(IdCourse);
        setGeocache1();
        setGeocache2();
        setGeocache3();
        IndexTab = 0;
    }

    public static boolean CourseIsEnded(int IndexTab){
        if(IndexTab == 3)
        {
            return true;
        }
        else {
            return false;
        }
    }

}
