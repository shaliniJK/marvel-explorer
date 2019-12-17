package com.marvel_explorer.ui.characterdetails.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marvel_explorer.MarvelApplication;
import com.marvel_explorer.R;
import com.marvel_explorer.data.model.marvelentitytypes.Character;
import com.marvel_explorer.di.ApplicationComponent;
import com.marvel_explorer.ui.characterdetails.CharacterDetailsContract;
import com.marvel_explorer.ui.characterdetails.CharacterDetailsPresenter;
import com.marvel_explorer.ui.characterdetails.di.CharacterDetailsPresenterModule;
import com.marvel_explorer.ui.characterdetails.di.DaggerCharacterDetailsPresenterComponent;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CharacterDetailsFragment extends Fragment implements CharacterDetailsContract.View {

    private View mRootView;

    @Inject
    CharacterDetailsPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        ApplicationComponent appComponent = ((MarvelApplication) getActivity().getApplication()).getAppComponent();

        DaggerCharacterDetailsPresenterComponent.factory().create(appComponent, new CharacterDetailsPresenterModule(this)).inject(this);

        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRootView = inflater.inflate(R.layout.character_details, container, false);

        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mPresenter.attachView(this);

        mPresenter.fetchCharacter();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    public void displayCharacter(Character character) {

    }

}
