package com.daviddev.projetgocaching_intgration1;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DataBaseManager extends SQLiteOpenHelper implements BaseColumns{

    public static final String DATABASE_NAME = "/sdcard/Android/data/com.daviddev.projetgocaching_intgration1/salscaching.db";
    public static final String TABLE_NAME = "salscaching";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME_GEOCACHE = "geocache";
    public static final String TABLE_NAME_COURSE = "course";
    public static final String KEY_ID = "GeocacheID";
    public static final String LONGITUDE = "Longitude";
    public static final String LATITUDE = "Latitude";
    public static final String TITLE = "Title";
    public static final String QUESTION = "Question";
    public static final String ANSWER = "Answer";
    public static final String CHOICE_1 = "Choice_1";
    public static final String CHOICE_2 = "Choice_2";
    public static final String CHOICE_3 = "Choice_3";
    public static final String CLUE_1 = "Clue_1";
    public static final String CLUE_2 = "Clue_2";


    //Sont crées lors du démarrage de l'application dans StartActivity
    static DataBaseManager dbHelper;
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
        dbHelper = this;
        db = dbHelper.getReadableDatabase();
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

    public static Coordinates getCoordinates(int IDGeocache){

        String selectQuery;
        Cursor cursor;
        double latitude = 0, longitude = 0;

        selectQuery = "SELECT "+ LONGITUDE +" FROM " + TABLE_NAME_GEOCACHE+" WHERE "+KEY_ID+" = " +IDGeocache+" ";
        cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
                latitude = Double.parseDouble(cursor.getString(cursor.getColumnIndex(LONGITUDE)));
        }

        selectQuery = "SELECT " + LATITUDE +" FROM " + TABLE_NAME_GEOCACHE +" WHERE " + KEY_ID + " = " + IDGeocache+" ";
        cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            longitude = Double.parseDouble(cursor.getString(cursor.getColumnIndex(LATITUDE)));
        }

        Coordinates coords = new Coordinates(latitude, longitude);
        return coords;
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



}
