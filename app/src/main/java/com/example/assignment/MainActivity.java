package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    String choose, prsnName, prsnpsw;  //creating variables for spinner, username and password.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText username =(EditText) findViewById(R.id.username); // Empty field for entering username by user/admin.
        EditText passw =(EditText) findViewById(R.id.passw); // Empty field for entering password by user/admin.
        Spinner spnr = (Spinner) findViewById(R.id.spnr); //Choose between Admin and User.
        ArrayAdapter <CharSequence> spnrObjs = ArrayAdapter.createFromResource(this, R.array.choose, R.layout.spnr_clr);  // for spinner layout
        spnr.setAdapter(spnrObjs); // setting up spinner layout in login page.
        spnrObjs.setDropDownViewResource(R.layout.spnr_drwodn_clr);  //Setting background color of spinner.
        MaterialButton logbutn = (MaterialButton) findViewById(R.id.logbutn); // Login button

        logbutn.setOnClickListener(new View.OnClickListener(){  // after clicking login button users/admin redirects to profile page.
            @Override
            public void onClick(View v) {
                choose = spnr.getSelectedItem().toString();
                prsnName = username.getText().toString();
                prsnpsw = passw.getText().toString();


                // Only specific username and password of user or admin are given access to log in to the mobile application.
                if(username.getText().toString().trim().equals("Rebika") && passw.getText().toString().equals("12345") && choose.equals("Admin")) {
                    Intent intnt = new Intent(MainActivity.this, MyGames.class);   // redirects user from mainactivity to mygames.
                    intnt.putExtra("info1", prsnName); // fetches username for another java class.
                    intnt.putExtra("info2", prsnpsw); // fetches password for another java class.
                    intnt.putExtra("info3", choose); // fetches spinner (user or admin) for another java class.
                    startActivity(intnt);
                }
                else if(username.getText().toString().trim().equals("Pawan") && passw.getText().toString().trim().equals("12345")  && choose.equals("User")) {
                    Intent intnt = new Intent(MainActivity.this, MyGames.class);
                    intnt.putExtra("info1", prsnName);
                    intnt.putExtra("info2", prsnpsw);
                    intnt.putExtra("info3", choose);
                    startActivity(intnt);
                }
                else if(username.getText().toString().trim().equals("Pratik") && passw.getText().toString().trim().equals("12345") && choose.equals("User")) {
                    Intent intnt = new Intent(MainActivity.this, MyGames.class);
                    intnt.putExtra("info1", prsnName);
                    intnt.putExtra("info2", prsnpsw);
                    intnt.putExtra("info3", choose);
                    startActivity(intnt);
                }
                else if(username.getText().toString().trim().equals("Aarav") && passw.getText().toString().trim().equals("12345") && choose.equals("User")) {
                    Intent intnt = new Intent(MainActivity.this, MyGames.class);
                    intnt.putExtra("info1", prsnName);
                    intnt.putExtra("info2", prsnpsw);
                    intnt.putExtra("info3", choose);
                    startActivity(intnt);
                }
                else{
                    Toast.makeText(MainActivity.this, "LOGIN FAILED!", Toast.LENGTH_SHORT).show();  // shows alert message on bottom of device.
                }
            }
        });
    }
}