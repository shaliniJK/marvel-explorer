package com.marvel_explorer.ui.favorites.mapper;

import com.marvel_explorer.data.persistence.CharacterEntity;
import com.marvel_explorer.ui.home.adapter.CharacterViewModel;

import java.util.ArrayList;
import java.util.List;

public class CharacterEntityToViewModelMapper {

    public List<CharacterViewModel> map(List<CharacterEntity> characters) {
        List<CharacterViewModel> characterViewModels = new ArrayList<>();

        for (CharacterEntity character : characters) {
            characterViewModels.add(map(character));
        }

        return characterViewModels;
    }

    private CharacterViewModel map(CharacterEntity character) {
        CharacterViewModel characterViewModel = new CharacterViewModel();

        characterViewModel.setId(character.getId());
        characterViewModel.setDescription(character.getDescription());
        characterViewModel.setName(character.getName());
        characterViewModel.setThumbnailUrl(character.getImageUrl());

        return characterViewModel;
    }

}
