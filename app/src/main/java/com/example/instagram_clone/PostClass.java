package com.example.instagram_clone;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PostClass extends ArrayAdapter<String>{
    private final ArrayList<String> username;
    private final  ArrayList<String> comand;
    private final  ArrayList<Bitmap>  userImage;
    private final Activity context;

    public PostClass(Activity context, int resource, ArrayList<String> username, ArrayList<String> comand, ArrayList<Bitmap> userImage, Activity context1) {
        super(context,R.layout.custom_view,username);
        this.username = username;
        this.comand = comand;
        this.userImage = userImage;
        this.context = context1;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View view =inflater.inflate(R.layout.custom_view,null,true);
        TextView textView=view.findViewById(R.id.custom_virev_usernametext);
        TextView textcustom=view.findViewById(R.id.custom_view_commit_);
        ImageView imageView=view.findViewById(R.id.custom_view_image);
        textView.setText(username.get(position));
        textcustom.setText(comand.get(position));
        imageView.setImageBitmap(userImage.get(position));
        return view;
    }
}
