package com.marvel_explorer.ui.favorites;

import com.marvel_explorer.ui.home.adapter.CharacterViewModel;

import java.util.List;

public interface FavoritesContract {

    interface View {
        void displayFavorites(List<CharacterViewModel> characterViewModels);

        void onCharacterRemoved();

        void showCharacterDetails(String characterId);
    }

    interface Presenter {
        void getFavorites();

        void deleteCharacterFromFavorites(String characterId);

        void navigateToCharacterDetails(String characterId);

    }
}
