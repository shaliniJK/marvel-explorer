package com.marvel_explorer.di;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.marvel_explorer.data.persistence.CharacterDAO;
import com.marvel_explorer.data.persistence.MarvelDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    private MarvelDatabase mMarvelDatabase;

    public DatabaseModule (Application application) {
        mMarvelDatabase = Room.databaseBuilder(
                application,
                MarvelDatabase.class,
                "marvel-database"
        ).build();
    }

    @Provides
    @Singleton
    MarvelDatabase provideDatabase () {
        return mMarvelDatabase;
    }

    @Singleton
    @Provides
    public CharacterDAO provideCharacterDao(MarvelDatabase marvelDatabase){
        return marvelDatabase.mCharacterDAO();
    }

}
