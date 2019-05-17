package com.daviddev.projetgocaching_intgration1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class QuestionActivity extends Activity implements View.OnClickListener {

    TextView question, clue;
    String clue1, clue2, answer;
    Button choice_1;
    Button choice_2;
    Button choice_3;
    Intent intent;
    int failNbr;
    String attempt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        question = findViewById(R.id.question);
        question.setText(Geocache.getQuestion());

        answer = Geocache.getAnswer();

        clue = findViewById(R.id.clue);
        clue1 = Geocache.getClue_1();
        clue2 = Geocache.getClue_2();

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

        switch (view.getId()){
            case R.id.choice_1:
                attempt = (String) choice_1.getText();
                break;
            case R.id.choice_2:
                attempt = (String) choice_2.getText();
                break;
            case R.id.choice_3:
                attempt = (String) choice_3.getText();
                break;
        }

        if (attempt.equals(answer)){
            Toast.makeText(this, "Bien joué!", Toast.LENGTH_LONG).show();
            if (Course.isEnded()){
                intent = new Intent(this, EndActivity.class);
                startActivity(intent);
                this.finish();
            }
            else {
                intent = new Intent(this, PostAnswerActivity.class);
                startActivity(intent);
                this.finish();
            }
        }
        else {
            if (failNbr == 0) {
                clue.setVisibility(View.VISIBLE);
                clue.setText("Indice 1:\n" + clue1);
                failNbr++;
            } else {
                Toast.makeText(this, "La bonne réponse était:" + answer, Toast.LENGTH_LONG).show();
                if (Course.isEnded()) {
                    intent = new Intent(this, EndActivity.class);
                    startActivity(intent);
                    this.finish();
                } else {
                    intent = new Intent(this, PostAnswerActivity.class);
                    startActivity(intent);
                    this.finish();
                }
            }
        }
    }
}
