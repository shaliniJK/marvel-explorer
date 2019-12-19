package com.marvel_explorer.ui.search;

import com.marvel_explorer.ui.home.adapter.CharacterViewModel;

import java.util.List;

public interface SearchContract {

    interface View {
        void displayCharacters(List<CharacterViewModel> characterViewModelList);

        void onCharacterAddedToFavorites();

        void onCharacterRemovedFromFavorites();

        void showCharacterDetails(String characterId);
    }

    interface Presenter {

        void searchCharacters(String keywords);

        void addCharacterToFavorites(String characterId);

        void removeCharacterFromFavorites(String characterId);

        void navigateToCharacterDetails(String characterId);
    }
}
