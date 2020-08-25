package com.example.instagram_clone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class FeedActivity extends AppCompatActivity {

    ListView listView;
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
    private static byte[] readFileToByteArray(File file){
        FileInputStream fis = null;
        // Creating a byte array using the length of the file
        // file.length returns long which is cast to int
        byte[] bArray = new byte[(int) file.length()];
        try{
            fis = new FileInputStream(file);
            fis.read(bArray);
            fis.close();
        }catch(IOException ioExp){
            ioExp.printStackTrace();
        }
        return bArray;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        listView=findViewById(R.id.listview);
        final ArrayList<Bitmap> bitmapArrayList=new ArrayList<>();
        ParseQuery<ParseObject> parseQuery=new ParseQuery<ParseObject>("posts");
        parseQuery.whereContains("username",ParseUser.getCurrentUser().getUsername());
        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e!=null){
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }else{
                    for (ParseObject object:objects) {
                        ParseFile file=object.getParseFile("photo");
                        try {
                            File dosya =file.getFile();
                            byte[] bytes=readFileToByteArray(dosya);
                            Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                            bitmapArrayList.add(bitmap);
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                    }
                }

            }
        });
        ArrayAdapter<Bitmap>arrayAdapter =new ArrayAdapter<>(this,android.R.layout.select_dialog_item,bitmapArrayList);
        listView.setAdapter(arrayAdapter);

    }
}