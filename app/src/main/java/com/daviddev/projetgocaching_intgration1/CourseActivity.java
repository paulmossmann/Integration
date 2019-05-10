package com.daviddev.projetgocaching_intgration1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CourseActivity extends Activity implements View.OnClickListener {

    Intent intent;
    Button course1, course2;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        int[] coursesIds = Course.getCoursesIds();

        course1 = findViewById(R.id.course1);
        course1.setOnClickListener(this);
        course1.setText(Course.getCourseName(coursesIds[0]));

        course2 = findViewById(R.id.course2);
        course2.setOnClickListener(this);
        course2.setText(Course.getCourseName(coursesIds[2]));
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.course1){
            Course.setupCourse(1);
            intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
            this.finish();
        }
        else if (view.getId() == R.id.course2) {
            Course.setupCourse(3);
            intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
            this.finish();
        }
    }
}
