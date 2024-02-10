package com.nik.trivia_2.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.nik.trivia_2.controller.AppController;
import com.nik.trivia_2.model.Question;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    ArrayList<Question> questions = new ArrayList<>();
    String url = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";

    public List<Question> getQuestions(final AnswerListAsynResponse callback){

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, response -> {
            for (int i = 0; i <response.length() ; i++) {

                try {
                    Question question = new Question(response.getJSONArray(i).get(0).toString(),response.getJSONArray(i).getBoolean(1));
                    Log.d("Main", "onCreate" + response.getJSONArray(i).get(0));
                    Log.d("Man", "onCreate" + response.getJSONArray(i).getBoolean(1));

                    questions.add(question);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            if(callback!=null){
                callback.processFinished(questions);
            }



        },
                error -> {

                }

        );

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);

        return questions;
    }

}
