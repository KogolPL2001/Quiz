package com.example.quiz;
import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.Button;
import android.widget.TextView;


public class PromptActivity extends AppCompatActivity {

    private boolean correctAnswer;
    private TextView answerView;
    private Button showCorrectAnswerButton;
    public static final String KEY_EXTRA_ANSWER_SHOWN="answerShown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt);
        showCorrectAnswerButton = findViewById(R.id.show_correct_answer_button);
        answerView=findViewById(R.id.answer_view);
        correctAnswer = getIntent().getBooleanExtra(MainActivity.KEY_EXTRA_ANSWER, true);


        showCorrectAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int answer=correctAnswer ? R.string.button_true : R.string.button_false;
                answerView.setText(answer);
                setAnswerShownResult(true);
            }
        });
    }
    private void setAnswerShownResult(boolean answerWasShown)
    {
        Intent resultIntent=new Intent();
        resultIntent.putExtra(KEY_EXTRA_ANSWER_SHOWN,answerWasShown);
        setResult(RESULT_OK,resultIntent);
    }
}