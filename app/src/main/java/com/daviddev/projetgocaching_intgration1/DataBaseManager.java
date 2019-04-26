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
    public static final String TABLE_NAME = "salscaching";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "salscaching.db";
    public static final String TABLE_NAME_GEOCACHE = "geocache";
    public static final String KEY_ID = "GeocacheID";
    public static final String COORD_X = "CoordX";
    public static final String COORD_Y = "CoordY";
    static String selectQuery;


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_GEOCACHE + "("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COORD_X + "REAL, "
                    + COORD_Y + "REAL); ";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;


    public DataBaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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

    public static ArrayList<String> getGeocacheID(SQLiteDatabase db, int IDGeocache){
        ArrayList<String> ArrayList = new ArrayList<String>();
        String name="";
        String selectQuery = "SELECT "+COORD_Y+" FROM " + TABLE_NAME+" WHERE "+COORD_X +" = " +IDGeocache+" ";
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                name = c.getString(c.getColumnIndex(COORD_X));
                ArrayList.add(name);
            } while (c.moveToNext());
            Log.d("array", ArrayList.toString());
        }
        return ArrayList;
    }

    public static ArrayList<String> getCoordX(Context context, int IDGeocache){
        DataBaseManager dbHelper = new DataBaseManager(context);
        ArrayList<String> ArrayList = new ArrayList<String>();
        String name="";
        selectQuery = "SELECT "+COORD_X+" FROM " + TABLE_NAME_GEOCACHE+" WHERE "+KEY_ID+" = " +IDGeocache+" ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                name = c.getString(c.getColumnIndex(COORD_X));
                ArrayList.add(name);
            } while (c.moveToNext());
            Log.d("array", ArrayList.toString());
        }

        return ArrayList;

    }

    public static Coordinates getCoordinates(Context context, int IDGeocache){

        DataBaseManager dbHelper = new DataBaseManager(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
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



    public void getCoordY(){

    }

    public void setCoordX(){

    }

    public void setCoordY(){


    }

    /*public static ArrayList<String> test(Context context){
        DataBaseManager dbHelper = new DataBaseManager(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                COORD_X
        };

// Filter results WHERE "title" = 'My Title'
        String selection = KEY_ID + " = ?";
        String[] selectionArgs = { "3" };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                KEY_ID + " DESC";

        Cursor cursor = db.query(
                TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,          // don't group the rows
                null,            // don't filter by row groups
                sortOrder               // The sort order
        );
        List itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(KEY_ID));
            itemIds.add(itemId);
        }
        return itemIds;

        cursor.close();
    }*/

}
