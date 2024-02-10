package com.nik.trivia_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nik.trivia_2.util.Perf;

public class MainActivity3 extends AppCompatActivity {
    private int[] data= new int[4];

    private String high_score;
    private Button button;
    private TextView attempt_q;
    private TextView skip_q;
    private TextView highest;
    private  TextView curr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        data = getIntent().getExtras().getIntArray("nikhil");

        //high_score =
        button = findViewById(R.id.Restart_button);
        attempt_q = findViewById(R.id.Attempted_q);
        skip_q= findViewById(R.id.skip_q);
        highest = findViewById(R.id.highest_score);
        curr = findViewById(R.id.current_score);

        attempt_q.setText("Attempted question:-"+data[1]);
        skip_q.setText("Skiped Question:-"+data[2]);
        highest.setText("highest score :-"+data[3]);
        curr.setText("Current score:-"+data[0]);

        button.setOnClickListener(view -> {

            Intent intent = new Intent(MainActivity3.this,MainActivity.class);
            startActivity(intent);
        });







    }

}