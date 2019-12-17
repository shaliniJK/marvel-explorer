package com.marvel_explorer.ui.characterdetails;

import com.marvel_explorer.data.repository.MarvelResourceRepository;
import com.marvel_explorer.ui.base.BasePresenter;

import javax.inject.Inject;

public class CharacterDetailsPresenter extends BasePresenter<CharacterDetailsContract.View> implements CharacterDetailsContract.Presenter {

    private MarvelResourceRepository mRepository;

    @Inject
    public CharacterDetailsPresenter(MarvelResourceRepository marvelResourceRepository) {
        mRepository = marvelResourceRepository;
    }

    public void fetchCharacter() {

    }

}
