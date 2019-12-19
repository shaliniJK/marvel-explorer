package com.marvel_explorer.ui.home;

import com.marvel_explorer.data.model.marvelentitytypes.Character;
import com.marvel_explorer.data.repository.MarvelResourceRepository;
import com.marvel_explorer.di.ApplicationContext;
import com.marvel_explorer.ui.base.BasePresenter;
import com.marvel_explorer.ui.home.mapper.CharacterToViewModelMapper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 *
 * @author koodun
 */
@ApplicationContext
public class MarvelHomePresenter extends BasePresenter<MarvelHomeContract.View> implements MarvelHomeContract.Presenter {

    private MarvelResourceRepository mRepository;
    private CharacterToViewModelMapper mMapper;

    @Inject
    public MarvelHomePresenter(MarvelResourceRepository repository, CharacterToViewModelMapper mapper) {
        this.mRepository = repository;
        this.mMapper = mapper;
    }

    public void getAllCharacters() {
        this.cancelSubscription();
        this.mDisposable.add(
                mRepository.getAllCharactersListResponse()
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

    public void addCharacterToFavorites(String characterId) {}

    public void removeCharacterFromFavorites(String characterId) {}

    public void navigateToCharacterDetails(String characterId) {
        mView.showCharacterDetails(characterId);
    }


}
