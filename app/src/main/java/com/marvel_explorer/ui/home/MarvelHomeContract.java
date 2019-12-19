package com.marvel_explorer.ui.home;

import com.marvel_explorer.ui.home.adapter.CharacterViewModel;

import java.util.List;

/**
 * A simple interface to define the View and Presenter interfaces for the home fragment
 * The home fragment displays marvel characters in a recycler view with the list and grid options.
 *
 * @author koodun
 */
public interface MarvelHomeContract {

    interface View {

        void displayCharacters(List<CharacterViewModel> characterViewModelList);

        void onCharacterAddedToFavorites();

        void onCharacterRemovedFromFavorites();

        void showCharacterDetails(String characterId);
    }

    interface Presenter {

        void getAllCharacters();

        void addCharacterToFavorites(String characterId);

        void removeCharacterFromFavorites(String characterId);

        void navigateToCharacterDetails(String characterId);
    }
}
