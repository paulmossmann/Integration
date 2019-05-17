package com.daviddev.projetgocaching_intgration1;

import android.content.Context;
import android.support.test.rule.ActivityTestRule;

import com.daviddev.salscaching.DataBaseManager;
import com.daviddev.salscaching.StartActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataBaseManagerTest {
    @Rule
    public ActivityTestRule<com.daviddev.salscaching.StartActivity> mActivityRule = new ActivityTestRule(StartActivity.class);
    static Context context;
    private StartActivity StartActivity = null;

    @Before
    public void setUp() throws Exception {

        StartActivity = mActivityRule.getActivity();
        context = StartActivity.getContext();
        new DataBaseManager(context);

    }
/*
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
    }

    @Test
    public void onUpgrade() {
    }

    @Test
    public void onDowngrade1() {
    }*/

    @Test
    public void getTitle() {
        String actual;
        String expected = "Amphithéâtre";
        actual = DataBaseManager.getTitle(2);
        assertEquals(actual, expected);
    }

   /* @Test
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
*/

}