package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class Dice extends AppCompatActivity {
    int rolledTimes, p1win, p2win, draw;
    // initializing time the dice rolled, number of wins or draws gained by person1 and person2.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);
        Random random = new Random(); //class for creating random number.
        Intent intent = getIntent();
        String usrnmShow = intent.getStringExtra("inform1");  //fetching username.
        final String FILE_NAME = "dice_"+usrnmShow + ".txt";  //concatenating file name with the name of user currently logged in.

        ImageView dicepic1 = (ImageView) findViewById(R.id.rldDicePic);  // user's dice picture
        ImageView dicepic2 = (ImageView) findViewById(R.id.rldDicePic2); // bot's dice picture
        MaterialButton rollButton = (MaterialButton) findViewById(R.id.rollbutton);  // roll button
        MaterialButton savebtn = (MaterialButton) findViewById(R.id.savebtn);  // save button
        TextView rolTimes = (TextView) findViewById(R.id.rolTimes);

        TextView opntdice1 = (TextView) findViewById(R.id.opntdice1);  //currently logged in user's name.
        opntdice1.setText(usrnmShow.toUpperCase()); // currently logged in user's name set to upper case.
        rollButton.setOnClickListener(v -> {
            rolledTimes++;  //increasing rolledTimes by 1.
            String rlTmShow = "You rolled " + rolledTimes +" time(s)";
            rolTimes.setText(rlTmShow);
            int randomNum1 = random.nextInt(6) + 1;  //creates random number between 1 to 6
            int randomNum2 = random.nextInt(6) + 1;  //creates random number between 1 to 6
            int dice1show = getResources().getIdentifier("dice"+randomNum1, "drawable", "com.example.assignment");  //changes dice picture of user according to created random number (1-6).
            int dice2show = getResources().getIdentifier("dice"+randomNum2, "drawable", "com.example.assignment");  //changes dice picture of bot according to created random number (1-6).

            dicepic1.setImageResource(dice1show);  // shows dice pic of user
            dicepic2.setImageResource(dice2show);  // shows dice pic of bot
            TextView winnerDeclr = (TextView) findViewById(R.id.winnertxt);

            // if bot wins, it gets a point.
            if (randomNum1 < randomNum2){
                p2win++;
                String winnerShow = "Bot wins";
                winnerDeclr.setText(winnerShow);
            }
            // if user wins, they get a point.
            else if (randomNum1 > randomNum2){
                p1win++;
                String winnerShow = usrnmShow + " wins";
                winnerDeclr.setText(winnerShow);
            }

            // if both gets same dice, draw increases by 1.
            else{
                draw++;
                String winnerShow = "DRAW!";
                winnerDeclr.setText(winnerShow);
            }
        });


        // after clicking on save to file button, it saves the file name concatenated with user logged in currently.
        // it saves the username, current date and points scores in file.
        savebtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String winner;
                // fileoutput stream class is initialised to null.
                FileOutputStream filOutStr = null;
                //handling exceptions to throw errors.
                try{
                    //concatenating file name with username
                    filOutStr = openFileOutput(FILE_NAME, MODE_PRIVATE);
                    // if any of the player (user or bot) wins, their name is displayed.
                    if (p1win < p2win){
                        winner = "\nName: "+intent.getStringExtra("inform1")+"\nTotal Rolls: "+rolledTimes+"\nWins by "+ usrnmShow +": "+p1win+"\nWins by Bot: "+p2win+"\nDraw: " + draw +"\n\nWINNER: "+"Bot by " + (p2win - p1win) + " point(s)";
                    }
                    else  if (p1win > p2win){
                        winner = "\nName: "+intent.getStringExtra("inform1")+"\nTotal Rolls: "+rolledTimes+"\nWins by "+ usrnmShow +": "+p1win+"\nWins by Bot: "+p2win+"\nDraw: " + draw +"\n\nWINNER: "+ usrnmShow +" by " + (p1win - p2win) + " point(s)";
                    }
                    // if none wins, DRAW is displayed.
                    else{
                        winner = "\nName: "+intent.getStringExtra("inform1")+"\nTotal Rolls: "+rolledTimes+"\nWins by "+ usrnmShow +": "+p1win+"\nWins by Bot: "+p2win+"\nDraw: " + draw +"\n\nDRAW!";
                    }
                    // saves the context in .txt file.
                    filOutStr.write(winner.getBytes());
                    // alerts the message of file being saved on specific directory.
                    Toast.makeText(Dice.this, "Saved to " + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();

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

        });
    }
}