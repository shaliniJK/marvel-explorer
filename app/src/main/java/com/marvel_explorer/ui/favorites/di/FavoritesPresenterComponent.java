package com.marvel_explorer.ui.favorites.di;

import com.marvel_explorer.di.ApplicationComponent;
import com.marvel_explorer.di.ApplicationContext;
import com.marvel_explorer.ui.favorites.FavoritesPresenter;
import com.marvel_explorer.ui.favorites.fragment.FavoritesFragment;

import dagger.Component;

@ApplicationContext
@Component( modules = {FavoritesPresenterModule.class}, dependencies = {ApplicationComponent.class} )
public interface FavoritesPresenterComponent {

    @Component.Factory
    interface Factory {
        FavoritesPresenterComponent create(ApplicationComponent appComponent, FavoritesPresenterModule homePresenterModule);
    }

    void inject(FavoritesFragment favoritesFragment);

    void inject(FavoritesPresenter favoritesPresenter);
}

