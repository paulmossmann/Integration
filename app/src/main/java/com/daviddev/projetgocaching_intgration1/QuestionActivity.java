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
    int failNbr;
    String txt;

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
        int nbrChoice;
        switch (view.getId()){
            case R.id.choice_1:
                txt = (String) choice_1.getText();
                break;
            case R.id.choice_2:
                txt = (String) choice_2.getText();
                break;
            case R.id.choice_3:
                txt = (String) choice_3.getText();
                break;
        }

        if (txt == Geocache.getAnswer()){
            if (Course.isEnded()){
                intent = new Intent(this,EndActivity.class);
                startActivity(intent);
                this.finish();
            }
            else{
                Toast.makeText(this, "Bravo!", Toast.LENGTH_LONG).show();
                intent = new Intent(this,PostAnswerActivity.class);
                startActivity(intent);
                this.finish();
            }
        }
        else{
            Toast.makeText(this, "Mauvaise réponse!", Toast.LENGTH_LONG).show();
            failNbr++;

            if(failNbr == 1)
                clue.setVisibility(View.VISIBLE);
                clue.setText(Geocache.getClue_1());
            if(failNbr == 2)
                clue.setText(Geocache.getClue_2());
            if(failNbr == 3){

                if (Course.isEnded()){
                    intent = new Intent(this,EndActivity.class);
                    startActivity(intent);
                    this.finish();
                }
                else{
                    Toast.makeText(this, "La bonne réponse était: " + Geocache.getAnswer(), Toast.LENGTH_LONG).show();
                    intent = new Intent(this,PostAnswerActivity.class);
                    startActivity(intent);
                    this.finish();
                }
            }
        }


    }
}
