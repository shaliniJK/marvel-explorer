package com.marvel_explorer.ui.home.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.marvel_explorer.MarvelApplication;
import com.marvel_explorer.R;
import com.marvel_explorer.di.ApplicationComponent;
import com.marvel_explorer.ui.home.MarvelHomeContract;
import com.marvel_explorer.ui.home.MarvelHomePresenter;
import com.marvel_explorer.ui.home.adapter.CharacterActionInterface;
import com.marvel_explorer.ui.home.adapter.CharacterAdapter;
import com.marvel_explorer.ui.home.adapter.CharacterViewModel;
import com.marvel_explorer.ui.home.di.DaggerHomePresenterComponent;
import com.marvel_explorer.ui.home.di.HomePresenterModule;

import java.util.List;

import javax.inject.Inject;

public class HomeFragment extends Fragment implements MarvelHomeContract.View, CharacterActionInterface {

    private View mRootView;

    private CharacterAdapter mCharacterAdapter;
    private RecyclerView mRecyclerView;

    @Inject
    MarvelHomePresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        ApplicationComponent appComponent = ((MarvelApplication) getActivity().getApplication()).getAppComponent();

        DaggerHomePresenterComponent.factory().create(appComponent, new HomePresenterModule(this)).inject(this);

        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRootView = inflater.inflate(R.layout.fragment_home, container, false);

        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mPresenter.attachView(this);

        setupRecyclerView();

        mPresenter.getAllCharacters();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    private void setupRecyclerView() {
        mRecyclerView = mRootView.findViewById(R.id.recyclerView);

        if (mCharacterAdapter == null) {
            mCharacterAdapter = new CharacterAdapter();
        }

        mRecyclerView.setAdapter(mCharacterAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void displayCharacters(List<CharacterViewModel> characterViewModelList) {
        mCharacterAdapter.bindViewModels(characterViewModelList);
    }

    public void onCharacterAddedToFavorites() {}

    public void onCharacterRemovedFromFavorites() {}

    public void onFavoriteToggle(String characterId, boolean isFavorite) {}

}