package com.marvel_explorer.di;

import android.app.Application;
import android.content.Context;

import com.marvel_explorer.MarvelApplication;
import com.marvel_explorer.data.persistence.MarvelDatabase;
import com.marvel_explorer.data.repository.MarvelResourceRepository;
import com.marvel_explorer.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { ApplicationModule.class, NetworkModule.class, DatabaseModule.class, RepositoryModule.class })
public interface ApplicationComponent {

    Context getContext();

    Application getApplication();

    MarvelDatabase getMarvelDatabase();

    MarvelResourceRepository mRepository();

    void inject(MarvelApplication marvelApplication);

    void inject(MainActivity mainActivity);


}