package com.marvel_explorer.ui.favorites.di;

import com.marvel_explorer.data.repository.MarvelResourceRepository;
import com.marvel_explorer.di.ApplicationContext;
import com.marvel_explorer.ui.favorites.FavoritesContract;
import com.marvel_explorer.ui.favorites.FavoritesPresenter;
import com.marvel_explorer.ui.favorites.mapper.CharacterEntityToViewModelMapper;


import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module
public class FavoritesPresenterModule {

    private final FavoritesContract.View mView;

    @Inject
    MarvelResourceRepository mRepository;

    public FavoritesPresenterModule(FavoritesContract.View view) {
        this.mView = view;
    }

    @Provides
    FavoritesContract.View provideView() {
        return mView;
    }

    @Provides
    @ApplicationContext
    CharacterEntityToViewModelMapper provideMapper() {
        return new CharacterEntityToViewModelMapper();
    }

    @Provides
    @ApplicationContext
    FavoritesPresenter providePresenter(MarvelResourceRepository mRepository) {
        return new FavoritesPresenter(mRepository, provideMapper());
    }
}
