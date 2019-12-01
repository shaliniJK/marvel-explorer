package com.marvel_explorer.di;

import com.marvel_explorer.MarvelApplication;

import dagger.Component;

@ApplicationContext
@Component(modules = { ApplicationModule.class, NetworkModule.class })
public interface ApplicationComponent {

    void inject(MarvelApplication marvelApplication);

}