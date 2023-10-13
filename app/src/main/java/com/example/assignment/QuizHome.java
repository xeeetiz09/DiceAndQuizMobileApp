package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class QuizHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_home);
        MaterialButton letsbeginbtun = (MaterialButton) findViewById(R.id.letsbeginbtun);

        Intent intent = getIntent();
        String chosee = intent.getStringExtra("inform3"); //(user or admin?)
        String usrnmShow = intent.getStringExtra("inform1"); // username
        String dateShow = intent.getStringExtra("tddate");  //current date

        // redirects user to quiz page after clicked on Lets Begin button and some informations like
        // username, recent date and spinner (admin or user).
        letsbeginbtun.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intnt = new Intent(QuizHome.this, Quiz.class);
                intnt.putExtra("usrrnnm", usrnmShow);
                intnt.putExtra("datetday", dateShow);
                intnt.putExtra("choseenn", chosee);
                startActivity(intnt);
            }
        });
    }
}