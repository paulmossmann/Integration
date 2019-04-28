package com.daviddev.projetgocaching_intgration1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class QuestionActivity extends Activity implements View.OnClickListener {

    TextView question;
    TextView clue;
    Button choice_1;
    Button choice_2;
    Button choice_3;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        question = findViewById(R.id.question);
        question.setText(Geocache.getQuestion());

        clue = findViewById(R.id.clue);
        clue.setText("reponse:" + Geocache.getClue_1());

        choice_1 = findViewById(R.id.choice_1);
        choice_1.setText(Geocache.getChoice_1());
        choice_1.setOnClickListener(this);

        choice_2 = findViewById(R.id.choice_2);
        choice_2.setText(Geocache.getChoice_2());
        choice_2.setOnClickListener(this);

        choice_3 = findViewById(R.id.choice_3);
        choice_3.setText(Geocache.getChoice_3());
        choice_3.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
     /*   switch (view.getId()){

            case R.id.choice_1:
                if (choice_1.getText() == Geocache.getAnswer()) {
                    Toast.makeText(this, "Bonne réponse", Toast.LENGTH_SHORT);
                    intent = new Intent(this, MapsActivity.class);
                    startActivity(intent);
                    this.finish();
                }
                break;



        }*/

        intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
        this.finish();

    }
}
