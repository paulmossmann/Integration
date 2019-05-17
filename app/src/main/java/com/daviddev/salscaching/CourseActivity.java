package com.daviddev.salscaching;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.daviddev.projetgocaching_intgration1.R;

public class CourseActivity extends Activity implements View.OnClickListener {

    Intent intent;
    Button[] courses_buttons;
    int[] coursesIds;
    int coursesNbr;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        LinearLayout question_linear_layout = findViewById(R.id.question_linear_layout);

        coursesIds = Course.getCoursesIds();
        coursesNbr = coursesIds.length;
        courses_buttons = new Button[coursesNbr];

        int i;
        for(i = 0 ; i < coursesNbr; i++){

            courses_buttons[i] = new Button(this);
            courses_buttons[i].setText(Course.getCourseName(coursesIds[i]));
            courses_buttons[i].setOnClickListener(this);
            courses_buttons[i].setId(i);
            question_linear_layout.addView(courses_buttons[i]);

        }
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        if(id >= 0 && id < coursesNbr) {
            courses_buttons[view.getId()].setBackgroundColor(getResources().getColor(R.color.colorAccent));
            Course.setupCourse(coursesIds[id]);
            intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
            this.finish();
        }

    }
}
