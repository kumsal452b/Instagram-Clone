package com.example.instagram_clone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.zip.Inflater;

public class FeedActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=new MenuInflater(getApplicationContext());
        inflater.inflate(R.menu.mainmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.logout){
            ParseUser.logOutInBackground(new LogOutCallback() {
                @Override
                public void done(ParseException e) {
                    if (e!=null){
                        Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Logout success",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }
        if (item.getItemId()==R.id.addpost){
            //intent
           Intent intent=new Intent(getApplicationContext(),PostActivity.class);
           startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
    }
}