package com.example.instagram_clone;

import android.app.Application;

import com.parse.Parse;

public class ParrseServer extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .server("https://parseapi.back4app.com/\n")
                .applicationId("q2vou08N28mNr5k1J6lnPqLUSkC96VeFpo0gO5b9")
                .clientKey("MkCWxoSyjRq7ZeRIYbXx04Ds5uIsurezKJFS3pRE")
                .build()
        );
    }
}
