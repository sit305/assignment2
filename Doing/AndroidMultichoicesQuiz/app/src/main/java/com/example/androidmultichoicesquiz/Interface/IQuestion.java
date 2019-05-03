package com.example.androidmultichoicesquiz.Interface;

import com.example.androidmultichoicesquiz.Model.CurrentQuestion;

public interface IQuestion {
    CurrentQuestion getSelectedAnswer(); // Get selected Answer from user select
    void  showCorrectAnswer(); // Bold correct Answer text
    void  disableAnswer(); // Disable all check box
    void  resetQuestion(); // Reset all function on question
}
