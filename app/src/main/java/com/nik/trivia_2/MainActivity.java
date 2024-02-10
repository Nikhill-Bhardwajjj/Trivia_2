package com.nik.trivia_2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.nik.trivia_2.data.Repository;
import com.nik.trivia_2.databinding.ActivityMainBinding;
import com.nik.trivia_2.model.Question;
import com.nik.trivia_2.model.score;
import com.nik.trivia_2.util.Perf;

import java.util.List;

public class MainActivity extends AppCompatActivity  {
private ActivityMainBinding binding;
private Perf perf;
private int curr =0;
private Boolean answer ;
private int store_score;
private int score_counter =0;
private  score  score;
private int Attempt_question;
private int skip_question;
int [] arr = new int[4];


      List<Question> listeners;
      private final int correct_score=0;
      private final int attempt_question=0;
      private final int total_score = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
            perf= new Perf(MainActivity.this);
            score = new score();

           listeners =   new Repository().getQuestions(questionlist -> {


                       binding.questionText.setText(questionlist.get(curr).getAnswer());
                       binding.QuestionCounter.setText(String.format("Question:-%d/%d", (curr + 1) % listeners.size(), listeners.size()));

                   }
                      );

           //perv_high=getIntent().getIntExtra("perv_high",0);
           //score[2]=perv_high;

           binding.QuitButton.setOnClickListener(view -> {

               arr[1]=Attempt_question;
                arr[2]=skip_question;
               Intent intent = new Intent(MainActivity.this,MainActivity3.class);
              // intent.putExtra("nikhil",score);
               store_score = score.getScore();
               arr[0]=store_score;
               perf.save_high_score(arr[0]);
               arr[3] =perf.get_high_score();
               intent.putExtra("nikhil",arr);
               startActivity(intent);


           });

           binding.NextButton.setOnClickListener(view -> {

                  curr =( curr + 1) % listeners.size();
                  //score[1]++;
               skip_question++;




                  updatable();
                 // binding.questionText.setTextColor(Color.WHITE);
                  //binding.QuestionCounter.setText(String.format("Question:-%d/%d", (curr + 1) % listeners.size(), listeners.size()));
             //  total_score=correct_score/attempt_question;

              });
           binding.FalseButton.setOnClickListener(view -> {

                  answer= listeners.get(curr).isAnswerTrue();

                  if(!answer){

                      Toast.makeText(this,"Correct Answer",Toast.LENGTH_SHORT).show();
                      shake2_Animation();
                     //updatable();
                    //score[0]++;
                      //score[1]++;
                      addPoint();
                      Attempt_question++;

                  }
                  else
                  {   shakeAnimation();
                      Toast.makeText(this,"Incorrect Answer",Toast.LENGTH_SHORT).show();
                    //  updatable();
                     //score[1]++;
                      minus_point();
                      Attempt_question++;
                  }
                // total_score=correct_score/attempt_question;
                  updatable();
                 // Handler handler = new Handler(Looper.getMainLooper());
               final Handler handler = new Handler();
               handler.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       //Do something after 10000ms
                       curr++;
                       updatable();

                   }
               }, 900);

                  //


              });

           binding.TrueButton.setOnClickListener(view -> {

                  answer= listeners.get(curr).isAnswerTrue();

                  if(answer) {


                     Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show();
                     shake2_Animation();
                      addPoint();
                      Attempt_question++;
                  }
                  else{
                      shakeAnimation();

                      Toast.makeText(this, "Incorrect Answer",Toast.LENGTH_SHORT).show();
                      minus_point();
                      Attempt_question++;

                  }

                 updatable();
               final Handler handler = new Handler();
               handler.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       //Do something after 10000ms
                       curr++;
                       updatable();

                   }
               }, 900);





              });



    }

    private void updatable() {
        String q = listeners.get(curr).getAnswer();
        binding.questionText.setText(q);
        binding.QuestionCounter.setText(String.format("Question:-%d/%d", (curr + 1) % listeners.size(), listeners.size()));


    }

    public  void shakeAnimation() {

        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake_animation);
        binding.QuestionHolder.setAnimation(shake);
        binding.scoreCount.setAnimation(shake);
        shake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.questionText.setTextColor(Color.RED);
                binding.scoreCount.setTextColor(Color.RED);

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.questionText.setTextColor(Color.WHITE);
                binding.scoreCount.setTextColor(Color.WHITE);
                //updatable();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void shake2_Animation(){

        Animation shake2= AnimationUtils.loadAnimation(this , R.anim.shake2_animation);
        binding.QuestionHolder.setAnimation(shake2);
        binding.scoreCount.setAnimation(shake2);
        shake2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.questionText.setTextColor(Color.GREEN);
                binding.scoreCount.setTextColor(Color.GREEN);

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.questionText.setTextColor(Color.WHITE);
                binding.scoreCount.setTextColor(Color.WHITE);
               // updatable();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    private  void addPoint (){

        score_counter+=100;
        score.setScore(score_counter);
        binding.scoreCount.setText("Current score:-"+score.getScore() );

    }
    private void minus_point(){
        score_counter-=50;

      if(score_counter<0){
          score_counter = 0 ;
          score.setScore(0);


      }
      else{
          score.setScore(score_counter);
      }
        binding.scoreCount.setText("Current score:-"+score.getScore());



    }


 //  // @Override
 //   protected void onPause() {
 //       super.onPause();
    //    perf.save_high_score(store_score);



   // }




    }