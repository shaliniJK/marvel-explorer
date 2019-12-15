package com.marvel_explorer.ui.home.di;

import com.marvel_explorer.di.ApplicationComponent;
import com.marvel_explorer.di.ApplicationContext;
import com.marvel_explorer.ui.home.MarvelHomePresenter;
import com.marvel_explorer.ui.home.fragment.HomeFragment;

import dagger.Component;

@ApplicationContext
@Component( modules = {HomePresenterModule.class}, dependencies = {ApplicationComponent.class} )
public interface HomePresenterComponent {

    @Component.Factory
    interface Factory {
        HomePresenterComponent create(ApplicationComponent appComponent, HomePresenterModule homePresenterModule);
    }

    void inject(HomeFragment homeFragment);

    void inject(MarvelHomePresenter marvelHomePresenter);

}
