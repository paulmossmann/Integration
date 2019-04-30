package com.daviddev.projetgocaching_intgration1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

public class EndActivity extends Activity implements View.OnClickListener{

    Intent intent;
    Button end_button;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        end_button = findViewById(R.id.end_button);
        end_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.end_button){
            intent = new Intent(this, StartActivity.class);
            startActivity(intent);
            this.finish();
        }

    }
}
