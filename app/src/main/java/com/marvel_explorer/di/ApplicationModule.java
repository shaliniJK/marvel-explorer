package com.marvel_explorer.di;

import android.app.Application;
import android.content.Context;

import com.marvel_explorer.data.repository.MarvelResourceDataRepository;
import com.marvel_explorer.data.repository.MarvelResourceRepository;
import com.marvel_explorer.data.repository.local.MarvelResourceLocalDataSource;
import com.marvel_explorer.data.repository.remote.MarvelResourceRemoteDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application provideContext() {
        return mApplication;
    }

}
