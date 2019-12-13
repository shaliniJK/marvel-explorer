package com.marvel_explorer.data.repository;

import com.marvel_explorer.data.model.marvelentitytypes.Character;
import com.marvel_explorer.data.model.marvelentitytypes.Comic;
import com.marvel_explorer.data.model.marvelentitytypes.Creator;

import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Single;

/**
 *
 * @author koodun
 */
@Singleton
public interface MarvelResourceRepository {

    Single<List<Character>> getAllCharactersListResponse();

    Single<List<Character>> getCharactersListResponse(String keyword);

    Single<Character> getCharacterResponse(long characterId);

    Single<List<Comic>> getComicsListResponse(String keyword);

    Single<Comic> getComicResponse(long comicId);

    Single<List<Creator>> getCreatorsListResponse(String keyword);

    Single<Creator> getCreatorResponse(long creatorId);

//    Flowable<List<MarvelResourceEntity>> getFavouriteMarvelResources();

//    Completable addMarvelResourceToFavourites(String id);

//    Completable removeMarvelResourceToFavourites(String id);

}
