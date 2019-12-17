package com.marvel_explorer.ui.characterdetails.di;

import com.marvel_explorer.di.ApplicationComponent;
import com.marvel_explorer.di.ApplicationContext;
import com.marvel_explorer.ui.characterdetails.CharacterDetailsPresenter;
import com.marvel_explorer.ui.characterdetails.fragment.CharacterDetailsFragment;

import dagger.Component;

@ApplicationContext
@Component( modules = {CharacterDetailsPresenterModule.class}, dependencies = {ApplicationComponent.class} )
public interface CharacterDetailsPresenterComponent {

    @Component.Factory
    interface Factory {
        CharacterDetailsPresenterComponent create(ApplicationComponent appComponent, CharacterDetailsPresenterModule presenterModule);
    }

    void inject(CharacterDetailsFragment characterDetailsFragment);

    void inject(CharacterDetailsPresenter characterDetailsPresenter);
}
