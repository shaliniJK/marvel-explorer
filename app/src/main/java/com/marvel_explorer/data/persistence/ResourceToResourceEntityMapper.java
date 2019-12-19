package com.marvel_explorer.data.persistence;

import com.marvel_explorer.data.model.marvelentitytypes.Character;

public class ResourceToResourceEntityMapper {

    public CharacterEntity mapCharacter(Character character) {
        CharacterEntity characterEntity = new CharacterEntity();

        characterEntity.setId(character.getId());
        characterEntity.setImageUrl(character.getThumbnail().getFullImageUrl());
        characterEntity.setDescription(character.getDescription());
        characterEntity.setName(character.getName());

        return characterEntity;
    }

}
