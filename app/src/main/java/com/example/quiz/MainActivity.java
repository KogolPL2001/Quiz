package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView questionTextView;
    private int currentIndex=0;
    private Question[] questionsList= new Question[] {
            new Question(R.string.first_q,true),
            new Question(R.string.second_q,true),
            new Question(R.string.third_q,false),
            new Question(R.string.fourth_q,true),
            new Question(R.string.fifth_q,true)

    };
    private void setNextQuestion(){
        questionTextView.setText(questionsList[currentIndex].getQuestionId());
    }
    private void checkAnswerCorrectness(boolean userAnswer)
    {
        boolean correctAnswer=questionsList[currentIndex].isTrueAnswer();
        int resultMessageId=0;
        if(userAnswer==correctAnswer)
        {
            resultMessageId=R.string.correct_answer;
        }
        else
        {
            resultMessageId=R.string.incorrect_answer;
        }
        Toast.makeText(this,resultMessageId,Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        questionTextView = findViewById(R.id.textView);
        questionTextView.setText(questionsList[currentIndex].getQuestionId());
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerCorrectness(true);
            }
        });
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerCorrectness(false);
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex=(currentIndex+1)% questionsList.length;
                setNextQuestion();
            }
        });
    }

}