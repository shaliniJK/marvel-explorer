package com.marvel_explorer;

import android.app.Application;

import com.marvel_explorer.di.ApplicationComponent;
import com.marvel_explorer.di.ApplicationModule;
import com.marvel_explorer.di.DaggerApplicationComponent;
import com.marvel_explorer.di.DatabaseModule;

public class MarvelApplication extends Application {

    private ApplicationComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        sAppComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .databaseModule(new DatabaseModule(this))
                .build();
        sAppComponent.inject(this);
    }

    public ApplicationComponent getAppComponent() {
        return sAppComponent;
    }
}
