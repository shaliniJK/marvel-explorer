package com.marvel_explorer.ui.favorites.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.marvel_explorer.MarvelApplication;
import com.marvel_explorer.R;
import com.marvel_explorer.di.ApplicationComponent;
import com.marvel_explorer.ui.characterdetails.fragment.CharacterDetailsFragment;
import com.marvel_explorer.ui.favorites.FavoritesContract;
import com.marvel_explorer.ui.favorites.FavoritesPresenter;
import com.marvel_explorer.ui.favorites.adapter.CharacterEntityAdapter;
import com.marvel_explorer.ui.favorites.adapter.FavoritesActionInterface;
import com.marvel_explorer.ui.favorites.di.FavoritesPresenterModule;
import com.marvel_explorer.ui.home.adapter.CharacterViewModel;
import com.marvel_explorer.ui.favorites.di.DaggerFavoritesPresenterComponent;

import java.util.List;

import javax.inject.Inject;

public class FavoritesFragment extends Fragment implements FavoritesContract.View, FavoritesActionInterface {

    private View mRootView;
    private CharacterEntityAdapter mCharacterEntityAdapter;
    private RecyclerView mRecyclerView;
    private boolean grid_list_flag = true;

    @Inject
    FavoritesPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        ApplicationComponent appComponent = ((MarvelApplication) getActivity().getApplication()).getAppComponent();

        DaggerFavoritesPresenterComponent.factory().create(appComponent, new FavoritesPresenterModule(this)).inject(this);

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
        mPresenter.getFavorites();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    private void setupRecyclerView() {
        mRecyclerView = mRootView.findViewById(R.id.recyclerView);

        if (mCharacterEntityAdapter == null) {
            mCharacterEntityAdapter = new CharacterEntityAdapter(this);
        }
        mRecyclerView.setAdapter(mCharacterEntityAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(R.id.back_button).setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.grid_list_switch:
                if (grid_list_flag) {
                    // Show grid view
                    mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                    item.setIcon(R.drawable.ic_list_24px);
                } else {
                    // Show list view
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    item.setIcon(R.drawable.ic_grid_on_24px);
                }
                grid_list_flag = !grid_list_flag;

                return true;
            case R.id.back_button:

                return false;
            default:
                break;
        }
        return false;
    }

    public void displayFavorites(List<CharacterViewModel> characterViewModels) {
        mCharacterEntityAdapter.bindViewModels(characterViewModels);
    }

    public void onCharacterRemoved() {
    }

    public void onRemoveFavoriteCharacter(String characterId) {
        mPresenter.deleteCharacterFromFavorites(characterId);
    }

    public void showCharacterDetails(String characterId) {
        CharacterDetailsFragment characterDetailsFragment = CharacterDetailsFragment.newInstance(characterId);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(((ViewGroup)getView().getParent()).getId(), characterDetailsFragment, characterId)
                .addToBackStack(null)
                .commit();
    }

    public void onItemClicked(String characterId) {
        mPresenter.navigateToCharacterDetails(characterId);
    }
}