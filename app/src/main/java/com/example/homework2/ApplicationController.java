package com.example.homework2;


import android.app.Application;

public class ApplicationController extends Application {
    private static ApplicationController instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static ApplicationController getInstance() {
        return instance;
    }
}
