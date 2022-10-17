package com.example.quiz;
import android.content.Intent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button promptButton;
    private boolean answerWasShown;
    private TextView questionTextView;
    private int currentIndex;
    private static final String KEY_CURRENT_INDEX="currentIndex";
    public static final String KEY_EXTRA_ANSWER="correctAnswer";
    private static final int REQUEST_CODE_PROMPT=0;
    private Question[] questionsList= new Question[] {
            new Question(R.string.first_q,true),
            new Question(R.string.second_q,true),
            new Question(R.string.third_q,false),
            new Question(R.string.fourth_q,true),
            new Question(R.string.fifth_q,true)

    };
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        Log.d("MainActivity","Wywołana została metoda: onSaveInstanceState");
        outState.putInt(KEY_CURRENT_INDEX,currentIndex);
    }
    private void setNextQuestion(){
        questionTextView.setText(questionsList[currentIndex].getQuestionId());
    }
    private void checkAnswerCorrectness(boolean userAnswer)
    {
        boolean correctAnswer=questionsList[currentIndex].isTrueAnswer();
        int resultMessageId=0;
        if(answerWasShown){
            resultMessageId=R.string.answer_was_shown;
        }
        else {
            if (userAnswer == correctAnswer) {
                resultMessageId = R.string.correct_answer;
            } else {
                resultMessageId = R.string.incorrect_answer;
            }
        }
        Toast.makeText(this,resultMessageId,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity","Wywołano on start");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d("MainActivity","Wywołano on postresume");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity","Wywołano on Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity","Wywołano on Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity","Wywołano on destroy");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MainActivity", "Wywołana została metoda cyklu życia: on Create");
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_CURRENT_INDEX);
        }
        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        promptButton = findViewById(R.id.prompt_button);
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
                currentIndex = (currentIndex + 1) % questionsList.length;
                answerWasShown=false;
                setNextQuestion();
            }
        });
        promptButton.setOnClickListener((v) -> {
            Intent intent = new Intent(MainActivity.this, PromptActivity.class);
            boolean correctAnswer = questionsList[currentIndex].isTrueAnswer();
            intent.putExtra(KEY_EXTRA_ANSWER, correctAnswer);
            startActivityForResult(intent,REQUEST_CODE_PROMPT);

        });
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,@Nullable Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode!=RESULT_OK){return;}
        if(requestCode==REQUEST_CODE_PROMPT) {
        if(data==null){return;}
        answerWasShown=data.getBooleanExtra(PromptActivity.KEY_EXTRA_ANSWER_SHOWN,false);
        }

    }
}