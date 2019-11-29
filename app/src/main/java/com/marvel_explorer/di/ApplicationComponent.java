package com.marvel_explorer.di;

import com.marvel_explorer.MarvelApplication;
import com.marvel_explorer.data.repository.MarvelResourceRepository;

import javax.inject.Singleton;

import dagger.Component;

@ApplicationContext
@Component(modules = { ApplicationModule.class, NetworkModule.class })
public interface ApplicationComponent {

    MarvelResourceRepository marvelResourceRepository();

    void inject(MarvelApplication marvelApplication);

}