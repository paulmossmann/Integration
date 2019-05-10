package com.daviddev.projetgocaching_intgration1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;
import java.util.ArrayList;
import java.io.File;

public class DataBaseManager extends SQLiteOpenHelper implements BaseColumns{

    public static final String DATABASE_NAME = "/sdcard/Android/data/com.sals.caching/BDD.db";
    public static final String TABLE_NAME = "salscaching";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME_GEOCACHE = "geocache";
    public static final String KEY_ID = "GeocacheID";
    public static final String QUESTION = "Question";
    public static final String ANSWER = "Answer";
    public static final String CHOICE_1 = "Choice_1";
    public static final String CHOICE_2 = "Choice_2";
    public static final String CHOICE_3 = "Choice_3";
    public static final String CLUE_1 = "Clue_1";
    public static final String CLUE_2 = "Clue_2";
    public static final String LONGITUDE = "Longitude";
    public static final String LATITUDE = "Latitude";
    public static final String TITLE = "Title";
    public static final String URL = "Url";

    public static final String TABLE_NAME_COURSE = "course";
    public static final String ID_COURSE = "Id_course";
    public static final String COURSE_NAME = "Name_course";
    public static final String ID_GEOCACHE_1 = "Id_geocache_1";
    public static final String ID_GEOCACHE_2 = "Id_geocache_2";
    public static final String ID_GEOCACHE_3 = "Id_geocache_3";

    static SQLiteDatabase db;

    private static final String SQL_CREATE_ENTRIES =
                    "CREATE TABLE IF NOT EXISTS "
                    + TABLE_NAME_GEOCACHE + "("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + LONGITUDE + "REAL, "
                    + LATITUDE + "REAL); ";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DataBaseManager(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = this.getReadableDatabase();
    }

    public static void CreatePath() {
        File folder = new File("/sdcard/Android/data/com.sals.caching");
        File folderimg = new File("/sdcard/Android/data/com.sals.caching/images");
        if (!folder.exists()) {
            folder.mkdir();
        }
        if (!folderimg.exists()) {
            folderimg.mkdir();
        }
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);

    }



    public static String getTitle(int IDGeocache){

        String selectQuery;
        Cursor cursor;
        String title = "";

        selectQuery = "SELECT "+TITLE+" FROM " + TABLE_NAME_GEOCACHE+" WHERE "+KEY_ID+" = " +IDGeocache+" ";
        cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            title = cursor.getString(cursor.getColumnIndex(TITLE));
        }

        return title;
    }

    public static String getQuestion(int IDGeocache){

        String selectQuery;
        Cursor cursor;
        String question = "";
        selectQuery = "SELECT "+QUESTION+" FROM " + TABLE_NAME_GEOCACHE+" WHERE "+KEY_ID+" = " +IDGeocache+" ";
        cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            question = cursor.getString(cursor.getColumnIndex(QUESTION));
        }

        return question;
    }

    public static String getAnswer(int IDGeocache){

        String selectQuery;
        Cursor cursor;
        String Answer = "";

        selectQuery = "SELECT "+ANSWER+" FROM " + TABLE_NAME_GEOCACHE+" WHERE "+KEY_ID+" = " +IDGeocache+" ";
        cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            Answer = cursor.getString(cursor.getColumnIndex(ANSWER));
        }

        return Answer;
    }

    public static String getChoice1(int IDGeocache){

        String selectQuery;
        Cursor cursor;
        String Choice1 = "";

        selectQuery = "SELECT "+CHOICE_1+" FROM " + TABLE_NAME_GEOCACHE+" WHERE "+KEY_ID+" = " +IDGeocache+" ";
        cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            Choice1 = cursor.getString(cursor.getColumnIndex(CHOICE_1));
        }

        return Choice1;
    }

    public static String getChoice2(int IDGeocache){

        String selectQuery;
        Cursor cursor;
        String Choice2 = "";

        selectQuery = "SELECT "+CHOICE_2+" FROM " + TABLE_NAME_GEOCACHE+" WHERE "+KEY_ID+" = " +IDGeocache+" ";
        cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            Choice2 = cursor.getString(cursor.getColumnIndex(CHOICE_2));
        }

        return Choice2;
    }

    public static String getChoice3(int IDGeocache){

        String selectQuery;
        Cursor cursor;
        String Choice3 = "";

        selectQuery = "SELECT "+CHOICE_3+" FROM " + TABLE_NAME_GEOCACHE+" WHERE "+KEY_ID+" = " +IDGeocache+" ";
        cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            Choice3 = cursor.getString(cursor.getColumnIndex(CHOICE_3));
        }

        return Choice3;
    }

    public static String getClue1(int IDGeocache){

        String selectQuery;
        Cursor cursor;
        String Clue1 = "";

        selectQuery = "SELECT "+CLUE_1+" FROM " + TABLE_NAME_GEOCACHE+" WHERE "+KEY_ID+" = " +IDGeocache+" ";
        cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            Clue1 = cursor.getString(cursor.getColumnIndex(CLUE_1));
        }

        return Clue1;
    }

    public static String getClue2(int IDGeocache){

        String selectQuery;
        Cursor cursor;
        String Clue2 = "";

        selectQuery = "SELECT "+CLUE_2+" FROM " + TABLE_NAME_GEOCACHE+" WHERE "+KEY_ID+" = " +IDGeocache+" ";
        cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            Clue2 = cursor.getString(cursor.getColumnIndex(CLUE_2));
        }

        return Clue2;
    }

    public static String getLongitude(int id){

        String selectQuery;
        Cursor cursor;
        String Longitude = "";

        int IDGeocache = Course.getNextGeocacheId();

        selectQuery = "SELECT "+LONGITUDE+" FROM " + TABLE_NAME_GEOCACHE+" WHERE "+KEY_ID+" = " +IDGeocache+" ";
        cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            Longitude = cursor.getString(cursor.getColumnIndex(LONGITUDE));
        }

        return Longitude;
    }

    public static String getLatitude(int id){

        String selectQuery;
        Cursor cursor;
        String Latitude = "";

        int IDGeocache = Course.getNextGeocacheId();

        selectQuery = "SELECT "+LATITUDE+" FROM " + TABLE_NAME_GEOCACHE+" WHERE "+KEY_ID+" = " +IDGeocache+" ";
        cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            Latitude = cursor.getString(cursor.getColumnIndex(LATITUDE));
        }

        return Latitude;
    }

    public static int[] getCoursesIds(){

        int tab[] = new int[3];
        String selectQuery;
        ArrayList<String> ArrayList = new ArrayList<String>();
        int i = 0;
        Cursor cursor;
        String CourseID = "";
        selectQuery = "SELECT "+ID_COURSE+" FROM " + TABLE_NAME_COURSE+" ";
        cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                CourseID = cursor.getString(cursor.getColumnIndex(ID_COURSE));
                ArrayList.add(CourseID);
                tab[i] = Integer.parseInt(ArrayList.get(i));
                i++;
            } while (cursor.moveToNext());
            Log.d("array", ArrayList.toString());
        }

        return tab;
    }

    public static int[] getAllCourse(int IDCourse){

        int tab[] = new int[3];
        tab[0] = getGeocache1(IDCourse);
        tab[1] = getGeocache2(IDCourse);
        tab[2] = getGeocache3(IDCourse);

        return tab;
    }

    public static int getGeocache1(int IDCourse){

        String selectQuery;
        Cursor cursor;
        String Geocache1 = "";

        selectQuery = "SELECT "+ID_GEOCACHE_1+" FROM " + TABLE_NAME_COURSE+" WHERE "+ID_COURSE+" = " +IDCourse+" ";
        cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            Geocache1 = cursor.getString(cursor.getColumnIndex(ID_GEOCACHE_1));
        }

        return Integer.parseInt(Geocache1);
    }

    public static int getGeocache2(int IDCourse){

        String selectQuery;
        Cursor cursor;
        String Geocache2 = "";

        selectQuery = "SELECT "+ID_GEOCACHE_2+" FROM " + TABLE_NAME_COURSE+" WHERE "+ID_COURSE+" = " +IDCourse+" ";
        cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            Geocache2 = cursor.getString(cursor.getColumnIndex(ID_GEOCACHE_2));
        }

        return Integer.parseInt(Geocache2);
    }

    public static int getGeocache3(int IDCourse){

        String selectQuery;
        Cursor cursor;
        String Geocache3 = "";

        selectQuery = "SELECT "+ID_GEOCACHE_3+" FROM " + TABLE_NAME_COURSE+" WHERE "+ID_COURSE+" = " +IDCourse+" ";
        cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            Geocache3 = cursor.getString(cursor.getColumnIndex(ID_GEOCACHE_3));
        }

        return Integer.parseInt(Geocache3);
    }

    public static String getCourseName(int IDCourse){

        String selectQuery;
        Cursor cursor;
        String Course_name = "";

        selectQuery = "SELECT "+COURSE_NAME+" FROM " + TABLE_NAME_COURSE+" WHERE "+ID_COURSE+" = " +IDCourse+" ";
        cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            Course_name = cursor.getString(cursor.getColumnIndex(COURSE_NAME));
        }

        return Course_name;
    }

    public static String getURL(int IDCourse){
        String selectQuery;
        Cursor cursor;
        String URL = "";

        selectQuery = "SELECT "+URL+" FROM " + TABLE_NAME_GEOCACHE+" WHERE "+ID_COURSE+" = " +IDCourse+" ";
        cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            URL = cursor.getString(cursor.getColumnIndex(URL));
        }

        return URL;


    }

}