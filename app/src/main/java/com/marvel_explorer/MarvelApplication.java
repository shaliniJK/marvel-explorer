package com.marvel_explorer;

import android.app.Application;

import com.marvel_explorer.di.ApplicationComponent;
import com.marvel_explorer.di.DaggerApplicationComponent;

public class MarvelApplication extends Application {

    private ApplicationComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        sAppComponent = DaggerApplicationComponent.create();

        sAppComponent.inject(this);
    }

    public ApplicationComponent getAppComponent() {
        return sAppComponent;
    }
}
