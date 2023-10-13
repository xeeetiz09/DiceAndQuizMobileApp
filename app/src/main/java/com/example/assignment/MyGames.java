package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Calendar;

public class MyGames extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_games);

        Intent intent = getIntent();  //to fetch intent data from another class.
        TextView dateShow = (TextView) findViewById(R.id.prfdatevw);
        Calendar todayDate = Calendar.getInstance();
        String viewTodayDate = "Date: "+DateFormat.getDateInstance().format(todayDate.getTime());
        dateShow.setText(viewTodayDate);  // shows current date.

        TextView usrnm = (TextView) findViewById(R.id.prfusrnm);
        String usrnmShow = "Username: "+intent.getStringExtra("info1");  //info1 (username) being taken from MainActivity and used here.
        usrnm.setText(usrnmShow);

        TextView usrpsw = (TextView) findViewById(R.id.usrpwd);
        String usrpwShow = "Password: "+intent.getStringExtra("info2");   //info2 (password) being taken from MainActivity and used here.
        usrpsw.setText(usrpwShow);

        TextView usrchosen = (TextView) findViewById(R.id.prfchosen);
        String choose = intent.getStringExtra("info3");
        String usrchosenShow = "You are logged in as "+choose+".";   //info3 (spinner) (admin/user) being taken from MainActivity and used here.
        usrchosen.setText(usrchosenShow);

        ImageView dicepic = (ImageView) findViewById(R.id.dicepic);   //taking dice picture and making it functionable when clicked.
        ImageView quizpic = (ImageView) findViewById(R.id.quizpic);   //taking quiz picture and making it functionable when clicked.

        dicepic.setOnClickListener(new View.OnClickListener(){

          @Override
          public void onClick(View v) {
              Intent intnt1 = new Intent(MyGames.this, Dice.class);
              intnt1.putExtra("inform1", getIntent().getStringExtra("info1"));
              startActivity(intnt1);
          }
      });

        quizpic.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intnt2 = new Intent(MyGames.this, QuizHome.class);
                intnt2.putExtra("inform1", getIntent().getStringExtra("info1"));
                intnt2.putExtra("tddate", viewTodayDate);  // taking todays date for another class (i.e. QuizHome.class)
                intnt2.putExtra("inform3", choose);
                startActivity(intnt2);
            }
        });
    }
}
