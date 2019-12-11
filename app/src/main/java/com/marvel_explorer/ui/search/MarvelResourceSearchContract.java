package com.marvel_explorer.ui.search;

import com.marvel_explorer.ui.search.adapter.SearchViewModel;

import java.util.List;

public interface MarvelResourceSearchContract {

    interface View {
        void displayMarvelResourceItems(List<SearchViewModel> someList);

        void onMarvelItemAddedToFavorites();

        void onMarvelItemRemovedFromFavorites();
    }

    interface Presenter {

        void searchMarvelResource(String keywords);

        void addMarvelItemToFavorite(String itemId);

        void removeMarvelItemFromFavorites(String itemId);

        void attachView(View view);

        void cancelSubscription();

        void detachView();
    }
}
