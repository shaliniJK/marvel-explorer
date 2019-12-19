package com.marvel_explorer.ui.search;

import com.marvel_explorer.data.model.marvelentitytypes.Character;
import com.marvel_explorer.data.repository.MarvelResourceRepository;
import com.marvel_explorer.ui.base.BasePresenter;
import com.marvel_explorer.ui.home.MarvelHomeContract;
import com.marvel_explorer.ui.home.mapper.CharacterToViewModelMapper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenter extends BasePresenter<SearchContract.View> implements SearchContract.Presenter {

    private MarvelResourceRepository mRepository;
    private CharacterToViewModelMapper mMapper;

    @Inject
    public SearchPresenter(MarvelResourceRepository repository, CharacterToViewModelMapper mapper) {
        this.mRepository = repository;
        this.mMapper = mapper;
    }

    public void searchCharacters(String keywords) {
        this.cancelSubscription();
        this.mDisposable.add(
                mRepository.getCharactersListResponse(keywords)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<Character>>() {

                            @Override
                            public void onSuccess(List<Character> characters) {
                                mView.displayCharacters(mMapper.map(characters));
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                            }

                        })
        );
    }

    public void addCharacterToFavorites(String characterId) {
        mDisposable.add(mRepository.addCharacterToFavorites(characterId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        mView.onCharacterAddedToFavorites();
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.err.println(e.getMessage());
                    }
                }));
    }

    public void removeCharacterFromFavorites(String characterId) {
        mDisposable.add(mRepository.deleteCharacterFromFavorites(characterId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        mView.onCharacterRemovedFromFavorites();
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.err.println(e.getMessage());
                    }
                }));
    }

    public void navigateToCharacterDetails(String characterId) {
        mView.showCharacterDetails(characterId);
    }

}
