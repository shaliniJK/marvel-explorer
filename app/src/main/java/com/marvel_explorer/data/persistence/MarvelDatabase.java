package com.marvel_explorer.data.persistence;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {CharacterEntity.class}, version = 1, exportSchema = false)
public abstract class MarvelDatabase extends RoomDatabase {

    public abstract CharacterDAO mCharacterDAO();
}
