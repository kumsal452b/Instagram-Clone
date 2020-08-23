package com.example.instagram_clone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button btnSgnIn,btnSgUp;
    ParseUser user;
    Intent intent;
    String pass;
    String kula;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=findViewById(R.id.editTextTextPersonName2);
        password=findViewById(R.id.editTextTextPassword);
        btnSgnIn=findViewById(R.id.btn_Sgnin);
        btnSgUp=findViewById(R.id.btn_Sgnup);
        user=new ParseUser();
        intent=new Intent(getApplicationContext(),FeedActivity.class);
        ParseUser parseUser=ParseUser.getCurrentUser();
        if (parseUser!=null){
            startActivity(intent);
            Toast.makeText(getApplicationContext(),"Welcome :"+parseUser.getUsername(),Toast.LENGTH_LONG).show();
        }
        btnSgUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e!=null){
                            e.fillInStackTrace();
                        }else{
                            Toast.makeText(getApplicationContext(),"Created success",Toast.LENGTH_LONG).show();
                            pass=password.getText().toString();
                        }
                    }
                });

            }
        });
        btnSgnIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (e!=null){
                            Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(),"Welcome :"+user.getUsername(),Toast.LENGTH_LONG).show();
                           startActivity(intent);
                        }

                    }
                });
            }
        });
    }
}