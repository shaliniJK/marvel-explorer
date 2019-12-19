package com.marvel_explorer.ui.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.marvel_explorer.MarvelApplication;
import com.marvel_explorer.R;
import com.marvel_explorer.di.ApplicationComponent;
import com.marvel_explorer.ui.characterdetails.fragment.CharacterDetailsFragment;
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
    private boolean grid_list_flag = true;

    @Inject
    MarvelHomePresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        ApplicationComponent appComponent = ((MarvelApplication) getActivity().getApplication()).getAppComponent();

        DaggerHomePresenterComponent.factory().create(appComponent, new HomePresenterModule(this)).inject(this);

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
            mCharacterAdapter = new CharacterAdapter(this);
        }
        mRecyclerView.setAdapter(mCharacterAdapter);
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

    public void displayCharacters(List<CharacterViewModel> characterViewModelList) {
        mCharacterAdapter.bindViewModels(characterViewModelList);
    }

    public void onCharacterAddedToFavorites() {}

    public void onCharacterRemovedFromFavorites() {}

    public void onFavoriteToggle(String characterId, boolean isFavorite) {
        if (isFavorite) {
            mPresenter.removeCharacterFromFavorites(characterId);
        } else {
            mPresenter.addCharacterToFavorites(characterId);
        }
    }

    public void onItemClicked(String characterId) {
        mPresenter.navigateToCharacterDetails(characterId);
    }

    public void showCharacterDetails(String characterId) {

        CharacterDetailsFragment characterDetailsFragment = CharacterDetailsFragment.newInstance(characterId);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(((ViewGroup)getView().getParent()).getId(), characterDetailsFragment, characterId)
                .addToBackStack(null)
                .commit();
    }

}