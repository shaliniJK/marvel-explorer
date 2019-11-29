package com.marvel_explorer.di;

import com.marvel_explorer.MarvelApplication;

import dagger.Component;

@Component(modules = { ApplicationModule.class })
public interface ApplicationComponent {

    void inject(MarvelApplication marvelApplication);

}