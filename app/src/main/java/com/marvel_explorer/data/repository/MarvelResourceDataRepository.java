package com.marvel_explorer.data.repository;

import com.marvel_explorer.data.model.marvelentitytypes.Character;
import com.marvel_explorer.data.model.marvelentitytypes.Comic;
import com.marvel_explorer.data.model.marvelentitytypes.Creator;
import com.marvel_explorer.data.persistence.CharacterEntity;
import com.marvel_explorer.data.repository.local.MarvelResourceLocalDataSource;
import com.marvel_explorer.data.repository.remote.MarvelResourceRemoteDataSource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Flowable;
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

    public Single<List<Character>> getAllCharactersListResponse() {
        return mRemoteDataSource.getAllCharactersListResponse();
    }

    public Single<List<Character>> getCharactersListResponse(String keyword) {
        return mRemoteDataSource.getCharactersListResponse(keyword);
    }

    public Single<Character> getCharacterResponse(String characterId) {
        return mRemoteDataSource.getCharacterResponse(characterId);
    }

    public Single<List<Comic>> getAllComicsListResponse() {
        return mRemoteDataSource.getAllComicsListResponse();
    }

    public Single<List<Comic>> getComicsListResponse(String keyword) {
        return mRemoteDataSource.getComicsListResponse(keyword);
    }

    public Single<Comic> getComicResponse(String comicId) {
        return mRemoteDataSource.getComicResponse(comicId);
    }

    public Single<List<Creator>> getCreatorsListResponse(String keyword)  {
        return mRemoteDataSource.getCreatorsListResponse(keyword);
    }

    public Single<Creator> getCreatorResponse(String creatorId) {
        return mRemoteDataSource.getCreatorResponse(creatorId);
    }

    public Flowable<List<CharacterEntity>> getFavoriteCharacters() {
        return mlocalDataSource.getFavoriteCharacters();
    }

    public Completable addToFavorites(String characterId) {
        return null;
    }

    public Completable deleteCharacterFromFavorites(String characterId) {
        return mlocalDataSource.deleteCharacterFromFavorites(characterId);
    }

}
