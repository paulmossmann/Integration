package com.daviddev.projetgocaching_intgration1;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class DataBaseManager extends SQLiteOpenHelper implements BaseColumns{

    public static final String DATABASE_NAME = "/storage/self/primary/Android/data/com.daviddev.projetgocaching_intgration1/bdd51.db";
    public static final String TABLE_NAME = "salscaching";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME_GEOCACHE = "geocache";
    public static final String KEY_ID = "GeocacheID";
    public static final String COORD_X = "CoordX";
    public static final String COORD_Y = "CoordY";
    public static final String TITLE = "Title";

    //Sont crée lors du démarage de l'application dans StartActivity
    static DataBaseManager dbHelper;
    static SQLiteDatabase db;

    private static final String SQL_CREATE_ENTRIES =
                    "CREATE TABLE IF NOT EXISTS "
                    + TABLE_NAME_GEOCACHE + "("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COORD_X + "REAL, "
                    + COORD_Y + "REAL); ";

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

        selectQuery = "SELECT "+COORD_X+" FROM " + TABLE_NAME_GEOCACHE+" WHERE "+KEY_ID+" = " +IDGeocache+" ";
        cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
                latitude = Double.parseDouble(cursor.getString(cursor.getColumnIndex(COORD_X)));
        }

        selectQuery = "SELECT " + COORD_Y +" FROM " + TABLE_NAME_GEOCACHE +" WHERE " + KEY_ID + " = " + IDGeocache+" ";
        cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            longitude = Double.parseDouble(cursor.getString(cursor.getColumnIndex(COORD_Y)));
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

}
