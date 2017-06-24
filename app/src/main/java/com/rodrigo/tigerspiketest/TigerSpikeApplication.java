package com.rodrigo.tigerspiketest;

import android.app.Application;

import com.rodrigo.tigerspiketest.di.AppComponent;
import com.rodrigo.tigerspiketest.di.DaggerAppComponent;

public class TigerSpikeApplication extends Application {

    public AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder().build();
    }
}
