package com.daviddev.projetgocaching_intgration1;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

public class QuestionActivity extends Activity {

    TextView question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        question = findViewById(R.id.question);

        Geocache.setAllTable(3);

        question.setText(Geocache.getQuestion());


    }
}
