package com.marvel_explorer.di;

import com.marvel_explorer.MarvelApplication;
import com.marvel_explorer.data.repository.MarvelResourceRepository;
import com.marvel_explorer.data.services.MarvelRetrofitService;
import com.marvel_explorer.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { ApplicationModule.class, NetworkModule.class, RepositoryModule.class})
public interface ApplicationComponent {

    MarvelResourceRepository mRepository();

    void inject(MarvelApplication marvelApplication);

    void inject(MainActivity mainActivity);


}