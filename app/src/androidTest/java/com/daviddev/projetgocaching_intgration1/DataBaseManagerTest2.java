package com.daviddev.projetgocaching_intgration1;

import android.support.test.rule.ActivityTestRule;
import android.content.Context;

import com.daviddev.salscaching.DataBaseManager;
import com.daviddev.salscaching.StartActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataBaseManagerTest2 {
    @Rule
    public ActivityTestRule<com.daviddev.salscaching.StartActivity> mActivityRule = new ActivityTestRule(StartActivity.class);
    static Context context;
    private StartActivity StartActivity = null;
    public static final String DATABASE_NAME = "/sdcard/Android/data/com.daviddev.projetgocaching_intgration1/salscaching2.db";
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

    public static final String TABLE_NAME_COURSE = "course";
    public static final String ID_COURSE = "Id_course";
    public static final String COURSE_NAME = "Name_course";
    public static final String ID_GEOCACHE_1 = "Id_geocache_1";
    public static final String ID_GEOCACHE_2 = "Id_geocache_2";
    public static final String ID_GEOCACHE_3 = "Id_geocache_3";

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

        StartActivity = mActivityRule.getActivity();
        context = StartActivity.getApplicationContext();
        new DataBaseManager(context);

    }

    @Test
    public void getTitle() {
        String actual;
        String expected = "Amphithéâtre";
        actual = DataBaseManager.getTitle(2);
        assertEquals(actual, expected);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getURL(){
        String actual;
        String expected = "/sdcard/Android/data/com.sals.caching/images/geo_1.jpg";
        actual = DataBaseManager.getURL(2);
        assertEquals(actual, expected);
    }


}