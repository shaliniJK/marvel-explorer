package com.marvel_explorer.data.repository;

import com.marvel_explorer.data.repository.local.MarvelResourceLocalDataSource;
import com.marvel_explorer.data.repository.remote.MarvelResourceRemoteDataSource;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * A class to handle data manipulation within the application
 *
 * @author koodun
 */
@Singleton
public class MarvelResourceDataRepository implements MarvelResourceRepository {

    private final MarvelResourceLocalDataSource mlocalDataSource;
    private final MarvelResourceRemoteDataSource mRemoteDataSource;

    @Inject
    public MarvelResourceDataRepository(MarvelResourceLocalDataSource localDataSource, MarvelResourceRemoteDataSource remoteDataSource) {
        mlocalDataSource  = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }
}
