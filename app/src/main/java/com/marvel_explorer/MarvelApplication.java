package com.marvel_explorer;

import android.app.Application;

import com.marvel_explorer.di.ApplicationComponent;
import com.marvel_explorer.di.DaggerApplicationComponent;
import com.marvel_explorer.di.NetworkModule;

public class MarvelApplication extends Application {

    private static ApplicationComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        sAppComponent = DaggerApplicationComponent.create();

    }
}
