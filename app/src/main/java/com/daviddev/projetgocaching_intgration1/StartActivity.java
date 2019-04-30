package com.daviddev.projetgocaching_intgration1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity implements View.OnClickListener{

    Button start_button;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        start_button = findViewById(R.id.start_button);
        start_button.setOnClickListener(this);

        new DataBaseManager(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.start_button){
            intent = new Intent(this, CourseActivity.class);
            startActivity(intent);
            this.finish();
        }

    }
}
