package com.marvel_explorer.ui.characterdetails.di;

import com.marvel_explorer.data.repository.MarvelResourceRepository;
import com.marvel_explorer.di.ApplicationContext;
import com.marvel_explorer.ui.characterdetails.CharacterDetailsContract;
import com.marvel_explorer.ui.characterdetails.CharacterDetailsPresenter;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module
public class CharacterDetailsPresenterModule {

    private final CharacterDetailsContract.View mView;

    @Inject
    MarvelResourceRepository mRepository;

    public CharacterDetailsPresenterModule(CharacterDetailsContract.View mView) {
        this.mView = mView;
    }

    @ApplicationContext
    @Provides
    CharacterDetailsContract.View provideView() {
        return mView;
    }

    @ApplicationContext
    @Provides
    CharacterDetailsPresenter providePresenter(MarvelResourceRepository marvelResourceRepository) {
        return new CharacterDetailsPresenter(marvelResourceRepository);
    }

}
