package com.marvel_explorer.data.repository;

import com.marvel_explorer.data.model.MarvelResourceResponse;
import com.marvel_explorer.data.model.marvelentitytypes.Character;
import com.marvel_explorer.data.model.marvelentitytypes.Comic;
import com.marvel_explorer.data.model.marvelentitytypes.Creator;
import com.marvel_explorer.data.repository.local.MarvelResourceLocalDataSource;
import com.marvel_explorer.data.repository.remote.MarvelResourceRemoteDataSource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

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

    public Single<List<Character>> getCharactersListResponse(String keyword) {
        return mRemoteDataSource.getCharactersListResponse(keyword);
    }

    public Single<Character> getCharacterResponse(long characterId) {
        return mRemoteDataSource.getCharacterResponse(characterId);
    }

    public Single<List<Comic>> getComicsListResponse(String keyword) {
        return mRemoteDataSource.getComicsListResponse(keyword);
    }

    public Single<Comic> getComicResponse(long comicId) {
        return mRemoteDataSource.getComicResponse(comicId);
    }

    public Single<List<Creator>> getCreatorsListResponse(String keyword)  {
        return mRemoteDataSource.getCreatorsListResponse(keyword);
    }

    public Single<Creator> getCreatorResponse(long creatorId) {
        return mRemoteDataSource.getCreatorResponse(creatorId);
    }

}
