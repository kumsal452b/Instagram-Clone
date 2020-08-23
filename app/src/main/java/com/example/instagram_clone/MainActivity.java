package com.example.instagram_clone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button btnSgnIn,btnSgUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=findViewById(R.id.editTextTextPersonName2);
        password=findViewById(R.id.editTextTextPassword);
        btnSgnIn=findViewById(R.id.btn_Sgnin);
        btnSgUp=findViewById(R.id.btn_Sgnup);
        btnSgUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
        btnSgnIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}