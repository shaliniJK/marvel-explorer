package com.marvel_explorer.ui.search.di;

import com.marvel_explorer.di.ApplicationComponent;
import com.marvel_explorer.di.ApplicationContext;
import com.marvel_explorer.ui.search.SearchPresenter;
import com.marvel_explorer.ui.search.fragment.SearchFragment;

import dagger.Component;

@ApplicationContext
@Component( modules = {SearchPresenterModule.class}, dependencies = {ApplicationComponent.class} )
public interface SearchPresenterComponent {

    @Component.Factory
    interface Factory {
        SearchPresenterComponent create(ApplicationComponent appComponent, SearchPresenterModule homePresenterModule);
    }

    void inject(SearchFragment searchFragment);

    void inject(SearchPresenter searchPresenter);

}
