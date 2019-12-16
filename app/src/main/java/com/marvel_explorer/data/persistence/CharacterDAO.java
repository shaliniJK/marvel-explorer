package com.marvel_explorer.data.persistence;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface CharacterDAO {

    @Query("SELECT * from characterentity")
    Flowable<List<CharacterEntity>> getFavoriteCharacters();

    @Query("SELECT id from characterentity")
    Single<List<String>> getFavoriteCharactersIdList();

    @Insert
    Completable addToFavorites(CharacterEntity characterEntity);

    @Query("DELETE FROM characterentity where id = :id")
    Completable deleteCharacterFromFavorites(String id);

}
