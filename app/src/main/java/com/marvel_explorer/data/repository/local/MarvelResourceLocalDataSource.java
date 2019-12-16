package com.marvel_explorer.data.repository.local;

import com.marvel_explorer.data.persistence.CharacterEntity;
import com.marvel_explorer.data.persistence.MarvelDatabase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * A class for handling Marvel Resources locally
 *
 * @author koodun
 */
public class MarvelResourceLocalDataSource {

    @Inject
    MarvelDatabase mMarvelDatabase;

    @Inject
    public MarvelResourceLocalDataSource(MarvelDatabase marvelDatabase) {
        this.mMarvelDatabase = marvelDatabase;
    }

    public Flowable<List<CharacterEntity>> getFavoriteCharacters() {
        return mMarvelDatabase.mCharacterDAO().getFavoriteCharacters();
    }

    public Completable addToFavorites(CharacterEntity characterEntity) {
        return mMarvelDatabase.mCharacterDAO().addToFavorites(characterEntity);
    }

    public Completable deleteCharacterFromFavorites(String characterId) {
        return mMarvelDatabase.mCharacterDAO().deleteCharacterFromFavorites(characterId);
    }

    public Single<List<String>> getFavoriteCharactersIdList() {
        return mMarvelDatabase.mCharacterDAO().getFavoriteCharactersIdList();
    }

}
