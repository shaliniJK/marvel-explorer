package com.marvel_explorer.ui.characterdetails;

import com.marvel_explorer.data.model.marvelentitytypes.Character;
import com.marvel_explorer.data.repository.MarvelResourceRepository;
import com.marvel_explorer.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CharacterDetailsPresenter extends BasePresenter<CharacterDetailsContract.View> implements CharacterDetailsContract.Presenter {

    private MarvelResourceRepository mRepository;

    @Inject
    public CharacterDetailsPresenter(MarvelResourceRepository marvelResourceRepository) {
        mRepository = marvelResourceRepository;
    }

    public void fetchCharacter(String characterId) {
        this.cancelSubscription();
        this.mDisposable.add(
                mRepository.getCharacterResponse(characterId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<Character>() {

                            @Override
                            public void onSuccess(Character character) {
                                mView.displayCharacter(character);
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                            }

                        })
        );
    }

}
