package com.marvel_explorer.ui.favorites;

import com.marvel_explorer.data.persistence.CharacterEntity;
import com.marvel_explorer.data.repository.MarvelResourceRepository;
import com.marvel_explorer.ui.base.BasePresenter;
import com.marvel_explorer.ui.favorites.mapper.CharacterEntityToViewModelMapper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class FavoritesPresenter extends BasePresenter<FavoritesContract.View> implements FavoritesContract.Presenter  {

    private MarvelResourceRepository mRepository;
    private CharacterEntityToViewModelMapper mMapper;

    @Inject
    public FavoritesPresenter(MarvelResourceRepository repository, CharacterEntityToViewModelMapper mapper) {
        this.mRepository = repository;
        this.mMapper = mapper;
    }

    public void getFavorites() {
        mDisposable.add(
                mRepository.getFavoriteCharacters()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new ResourceSubscriber<List<CharacterEntity>>() {
                            @Override
                            public void onNext(List<CharacterEntity> characterEntityList) {
                                mView.displayFavorites(mMapper.map(characterEntityList));
                            }

                            @Override
                            public void onComplete() {
                            }

                            @Override
                            public void onError(Throwable e) {
                                System.err.println(e.getMessage());
                            }
                        })
        );
    }

    public void deleteCharacterFromFavorites(String characterId) {
        mDisposable.add(
                mRepository.deleteCharacterFromFavorites(characterId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {
                                mView.onCharacterRemoved();
                            }

                            @Override
                            public void onError(Throwable e) {
                                System.err.println(e.getMessage());
                            }
                        })
        );
    }

    public void navigateToCharacterDetails(String characterId) {
        mView.showCharacterDetails(characterId);
    }

}
