package com.marvel_explorer.ui.search.di;

import com.marvel_explorer.data.repository.MarvelResourceRepository;
import com.marvel_explorer.di.ApplicationContext;
import com.marvel_explorer.ui.home.mapper.CharacterToViewModelMapper;
import com.marvel_explorer.ui.search.SearchContract;
import com.marvel_explorer.ui.search.SearchPresenter;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchPresenterModule {
    private final SearchContract.View mView;

    @Inject
    MarvelResourceRepository mRepository;

    public SearchPresenterModule(SearchContract.View view) {
        this.mView = view;
    }

    @Provides
    SearchContract.View provideView() {
        return mView;
    }

    @Provides
    @ApplicationContext
    CharacterToViewModelMapper provideMapper() {
        return new CharacterToViewModelMapper();
    }

    @Provides
    @ApplicationContext
    SearchPresenter providePresenter(MarvelResourceRepository mRepository) {
        return new SearchPresenter(mRepository, provideMapper());
    }

}
