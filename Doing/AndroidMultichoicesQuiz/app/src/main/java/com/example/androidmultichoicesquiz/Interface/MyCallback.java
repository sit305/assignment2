package com.example.androidmultichoicesquiz.Interface;

import com.example.androidmultichoicesquiz.Model.Question;

import java.util.List;

public interface MyCallback {
    void setQuestionList(List<Question> questionList);
}
