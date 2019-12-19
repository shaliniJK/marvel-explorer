package com.marvel_explorer.data.repository;

import com.marvel_explorer.data.model.marvelentitytypes.Character;
import com.marvel_explorer.data.model.marvelentitytypes.Comic;
import com.marvel_explorer.data.model.marvelentitytypes.Creator;
import com.marvel_explorer.data.persistence.CharacterEntity;

import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 *
 * @author koodun
 */
@Singleton
public interface MarvelResourceRepository {

    Single<List<Character>> getAllCharactersListResponse();

    Single<List<Character>> getCharactersListResponse(String keyword);

    Single<Character> getCharacterResponse(String characterId);

    Single<List<Comic>> getAllComicsListResponse();

    Single<List<Comic>> getComicsListResponse(String keyword);

    Single<Comic> getComicResponse(String comicId);

    Single<List<Creator>> getCreatorsListResponse(String keyword);

    Single<Creator> getCreatorResponse(String creatorId);

    Flowable<List<CharacterEntity>> getFavoriteCharacters();

    Completable addCharacterToFavorites(String characterId);

    Completable deleteCharacterFromFavorites(String characterId);

}
