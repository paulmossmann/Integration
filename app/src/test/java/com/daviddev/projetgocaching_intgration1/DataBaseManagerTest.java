/*package com.daviddev.projetgocaching_intgration1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static com.daviddev.projetgocaching_intgration1.DataBaseManager.KEY_ID;
import static com.daviddev.projetgocaching_intgration1.DataBaseManager.LATITUDE;
import static com.daviddev.projetgocaching_intgration1.DataBaseManager.LONGITUDE;
import static com.daviddev.projetgocaching_intgration1.DataBaseManager.TABLE_NAME;
import static com.daviddev.projetgocaching_intgration1.DataBaseManager.TABLE_NAME_GEOCACHE;
import static org.junit.Assert.*;


public class DataBaseManagerTest extends android.app.Activity{
    static SQLiteDatabase db;
    static Context context;

    @Rule
    //public ActivityTestRule<MapsActivity> mActivityRule = new ActivityTestRule(StartActivity.class);



    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS "
                    + TABLE_NAME_GEOCACHE + "("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + LONGITUDE + "REAL, "
                    + LATITUDE + "REAL); ";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    @Before
    public void setUp() throws Exception {
        new DataBaseManager(StartActivity.getContext());

        context = StartActivity.getContext();



    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getDatabaseName() {
    }

    @Test
    public void setWriteAheadLoggingEnabled() {
    }

    @Test
    public void setLookasideConfig() {
    }

    @Test
    public void setOpenParams() {
    }

    @Test
    public void setIdleConnectionTimeout() {
    }

    @Test
    public void getWritableDatabase() {
    }

    @Test
    public void getReadableDatabase() {
    }

    @Test
    public void close() {
    }

    @Test
    public void onConfigure() {
    }

    @Test
    public void onDowngrade() {
    }

    @Test
    public void onOpen() {
    }

    @Test
    public void onCreate() {
        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Test
    public void onUpgrade() {
    }

    @Test
    public void onDowngrade1() {
    }

    @Test
    public void getTitle() {
        String actual;
        String expected = "Amphitéâtre";
        actual = DataBaseManager.getTitle(2);
        assertEquals(actual, expected);
    }

    @Test
    public void getQuestion() {
    }

    @Test
    public void getAnswer() {
    }

    @Test
    public void getChoice1() {
    }

    @Test
    public void getChoice2() {
    }

    @Test
    public void getChoice3() {
    }

    @Test
    public void getClue1() {
    }

    @Test
    public void getClue2() {
    }

    @Test
    public void getLongitude() {
    }

    @Test
    public void getLatitude() {
    }

    @Test
    public void getCoursesIds() {
    }

    @Test
    public void getAllCourse() {
    }

    @Test
    public void getGeocache1() {
    }

    @Test
    public void getGeocache2() {
    }

    @Test
    public void getGeocache3() {
    }

    @Test
    public void getCourseName() {
    }

    @Test
    public void testadd() {
        int actual;
        int expected = 3;
        actual = DataBaseManager.testadd(2);
        assertEquals(actual, expected);
    }
}
*/