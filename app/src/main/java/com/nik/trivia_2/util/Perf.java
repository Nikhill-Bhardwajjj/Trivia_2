package com.nik.trivia_2.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Perf {

    private SharedPreferences preferences;

    public Perf(Activity context) {
        this.preferences = context.getPreferences(Context.MODE_PRIVATE);
    }

    public void save_high_score(int score ){
        int current_score = score;
        int last_score = preferences.getInt("nikhl",0 );

        if(current_score>last_score){

            preferences.edit().putInt("nikhil",score).apply();

        }




    }
    public int  get_high_score(){

        return preferences.getInt("nikhil",0);

    }
}
