package com.marvel_explorer.ui.home.mapper;

import com.marvel_explorer.data.model.marvelentitytypes.Character;
import com.marvel_explorer.ui.home.adapter.CharacterViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

/**
 * A simple class to map a Character to its corresponding CharacterViewModel
 *
 * @author koodun
 */
public class CharacterToViewModelMapper {

    public List<CharacterViewModel> map(List<Character> characters) {
        List<CharacterViewModel> characterViewModels = new ArrayList<>();

        for (Character character : characters) {
            characterViewModels.add(map(character));
        }

        return characterViewModels;
    }

    private CharacterViewModel map(Character character) {
        CharacterViewModel characterViewModel = new CharacterViewModel();

        characterViewModel.setName(character.getName());
        characterViewModel.setThumbnailUrl(character.getThumbnail().getFullImageUrl());

        return characterViewModel;
    }
}
