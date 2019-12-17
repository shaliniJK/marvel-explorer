package com.marvel_explorer.ui.home.di;

import com.marvel_explorer.data.repository.MarvelResourceRepository;
import com.marvel_explorer.di.ApplicationContext;
import com.marvel_explorer.ui.home.MarvelHomeContract;
import com.marvel_explorer.ui.home.MarvelHomePresenter;
import com.marvel_explorer.ui.home.mapper.CharacterToViewModelMapper;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module
public class HomePresenterModule {

    private final MarvelHomeContract.View mView;

    @Inject
    MarvelResourceRepository mRepository;

    public HomePresenterModule(MarvelHomeContract.View view) {
        this.mView = view;
    }

    @Provides
    MarvelHomeContract.View provideView() {
        return mView;
    }

    @Provides
    @ApplicationContext
    CharacterToViewModelMapper provideMapper() {
        return new CharacterToViewModelMapper();
    }

    @Provides
    @ApplicationContext
    MarvelHomePresenter providePresenter(MarvelResourceRepository mRepository) {
        return new MarvelHomePresenter(mRepository, provideMapper());
    }

}
