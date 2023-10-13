package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Quiz extends AppCompatActivity{
    TextView quizQuestions;  // Quiz Questions (String) initialized.
    Button ans1, ans2, ans3, ans4;   // options (button) initialized.
    int ttlQsns = QuizQuestions.question.length;  // total number of questions.
    int point = 0, curQsn = 0;   // total points/score scored and current question number initialized to 0.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        ans1 = findViewById(R.id.ans1);
        ans2 = findViewById(R.id.ans2);
        ans3 = findViewById(R.id.ans3);
        ans4 = findViewById(R.id.ans4);

        quizQuestions = findViewById(R.id.qzqsns);


        // after clicking on various buttons (options), if answer is correct, point increases by 1.

        ans1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (QuizQuestions.choosen[curQsn].equals(ans1.getText().toString())){
                    point++;
                }
                curQsn++;
                anotherQuestion();
            }
        });


        ans2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (QuizQuestions.choosen[curQsn].equals(ans2.getText().toString())){
                    point++;
                }
                curQsn++;
                anotherQuestion();
            }
        });

        ans3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (QuizQuestions.choosen[curQsn].equals(ans3.getText().toString())){
                    point++;
                }
                curQsn++;
                anotherQuestion();
            }
        });

        ans4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (QuizQuestions.choosen[curQsn].equals(ans4.getText().toString())){
                    point++;
                }
                curQsn++;
                anotherQuestion();
            }
        });
        anotherQuestion();
    }


    void anotherQuestion(){

        if (curQsn == ttlQsns){
            finishQuiz();
            return;
        }

        quizQuestions.setText(QuizQuestions.question[curQsn]);
        ans1.setText(QuizQuestions.options[curQsn][0]);
        ans2.setText(QuizQuestions.options[curQsn][1]);
        ans3.setText(QuizQuestions.options[curQsn][2]);
        ans4.setText(QuizQuestions.options[curQsn][3]);
    }
    // after questions gets finished, it shows an alert dialogue saying either passed or failed and a button which restarts the quiz.
    void finishQuiz(){
        String result = "";
        if(point > ttlQsns*0.40){
            result = "CONGRATULATIONS!";
        }
        else{
            result = "FAILED!";
        }
        new AlertDialog.Builder(this)
                .setTitle(result)
                .setMessage("You got "+point+" out of "+ttlQsns)
                .setPositiveButton("Restart", (dialogInterface, i) -> {
                    point = 0;
                    curQsn = 0;
                    anotherQuestion();
                })
                .setCancelable(false)
                .show();


        // to save the file by concatenating person's name and saving and showing data like name, score, current date
        Intent intent = getIntent();
        String winner;
        String choseen = intent.getStringExtra("choseenn");
        FileOutputStream filOutStr = null;
        String usrnmShow = intent.getStringExtra("usrrnnm");
        String dateShow = intent.getStringExtra("datetday");
        final String FILE_NAME = "quiz_"+usrnmShow + ".txt";
        try{
            filOutStr = openFileOutput(FILE_NAME, MODE_PRIVATE);
            if (point > ttlQsns*0.40){
                winner = "Congratulations!\n" + "Name of "+ choseen +": "+ usrnmShow +"\nCorrect Answers: " + point+"\nDate Logged In: "+dateShow;
            }
            else{
                winner = "Failed!\n"+ "\nName of "+ choseen +": "+ usrnmShow +"Correct Answers: " + point +"\nDate Logged In: "+dateShow;
            }

            //saves the context in .txt file
            filOutStr.write(winner.getBytes());
            // alerts the message of file being saved on specific directory.
            Toast.makeText(Quiz.this, "Saved to " + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            if (filOutStr != null){
                try{
                    filOutStr.close();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}