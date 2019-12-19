package com.marvel_explorer.di;

import com.marvel_explorer.data.persistence.MarvelDatabase;
import com.marvel_explorer.data.persistence.ResourceToResourceEntityMapper;
import com.marvel_explorer.data.repository.MarvelResourceDataRepository;
import com.marvel_explorer.data.repository.MarvelResourceRepository;
import com.marvel_explorer.data.repository.local.MarvelResourceLocalDataSource;
import com.marvel_explorer.data.repository.remote.MarvelResourceRemoteDataSource;
import com.marvel_explorer.data.services.MarvelService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    MarvelResourceRepository provideMarvelResourceRepository(MarvelResourceLocalDataSource localDataSource, MarvelResourceRemoteDataSource remoteDataSource, ResourceToResourceEntityMapper resourceEntityMapper) {
        return new MarvelResourceDataRepository(localDataSource, remoteDataSource, resourceEntityMapper);
    }

    @Provides
    @Singleton
    MarvelResourceRemoteDataSource provideRemoteDataSource(MarvelService marvelService) {
        return new MarvelResourceRemoteDataSource(marvelService);
    }

    @Provides
    @Singleton
    MarvelResourceLocalDataSource provideLocalDataSource(MarvelDatabase marvelDatabase) {
        return new MarvelResourceLocalDataSource(marvelDatabase);
    }

    @Provides
    @Singleton
    ResourceToResourceEntityMapper provideResourceToResourceEntityMapper() {
        return new ResourceToResourceEntityMapper();
    }

}