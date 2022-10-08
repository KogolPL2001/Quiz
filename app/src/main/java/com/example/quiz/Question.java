package com.example.quiz;

import android.widget.Button;
import android.widget.TextView;

public class Question {
    private int questionId;
    private boolean trueAnswer;
    public Question(int questionId,boolean trueAnswer){
        this.questionId=questionId;
        this.trueAnswer=trueAnswer;
    }
    public boolean isTrueAnswer()
    {
        return this.trueAnswer;
    }

    public int getQuestionId() {
        return questionId;
    }
}
