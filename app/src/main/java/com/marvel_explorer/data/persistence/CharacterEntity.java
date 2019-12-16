package com.marvel_explorer.data.persistence;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CharacterEntity {

    @NonNull
    @PrimaryKey
    public String id;
}
