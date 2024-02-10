package com.nik.trivia_2.data;

import com.nik.trivia_2.model.Question;

import java.util.ArrayList;

public interface AnswerListAsynResponse {

    void processFinished(ArrayList<Question>questionlist);

}
