package com.daviddev.projetgocaching_intgration1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

// !!! C'est cette activité qui crée un objet DbManager dés le lancement de l'appli
// Ceci permet d'éviter l'appel du "this" dans toutes tes méthdoes.

public class StartActivity extends AppCompatActivity {

    Button start;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        start = findViewById(R.id.start_button);
        new DataBaseManager(this);

        intent = new Intent(this, CourseActivity.class);
        startActivity(intent);
        this.finish();
    }

}
