package com.daviddev.projetgocaching_intgration1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CourseActivity extends Activity implements View.OnClickListener {

    Intent intent;
    Button button;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        button = findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        intent = new Intent(this, ScanActivity.class);
        startActivity(intent);
        this.finish();

    }
}
