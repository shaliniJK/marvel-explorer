package com.marvel_explorer.ui.characterdetails;

import com.marvel_explorer.data.model.marvelentitytypes.Character;

public interface CharacterDetailsContract {

    interface View {
        void displayCharacter(Character character);
    }

    interface Presenter {
        void fetchCharacter(String characterId);
    }
}
