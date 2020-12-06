package com.example.pixabay;

import android.app.Application;

public class UILApplication extends Application {
    private static UILApplication mInstance;
    public static Network restInstance = null;
    public static ItemHits selectedItemHits;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized UILApplication getInstance() {
        return mInstance;
    }
}
