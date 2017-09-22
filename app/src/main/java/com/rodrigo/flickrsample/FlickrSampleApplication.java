package com.rodrigo.flickrsample;

import android.app.Application;

import com.rodrigo.flickrsample.di.AppComponent;
import com.rodrigo.flickrsample.di.DaggerAppComponent;

public class FlickrSampleApplication extends Application {

    public AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder().build();
    }
}
