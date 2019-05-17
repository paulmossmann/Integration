package com.daviddev.salscaching;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.daviddev.projetgocaching_intgration1.R;

public class PostAnswerActivity extends Activity implements View.OnClickListener {

    Button valid_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postanswer);

        valid_button = findViewById(R.id.valid_button);
        valid_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.valid_button){
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
            this.finish();
        }
    }
}
