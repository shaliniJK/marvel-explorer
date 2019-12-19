package com.marvel_explorer.ui.search.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.marvel_explorer.MarvelApplication;
import com.marvel_explorer.R;
import com.marvel_explorer.di.ApplicationComponent;
import com.marvel_explorer.ui.characterdetails.fragment.CharacterDetailsFragment;
import com.marvel_explorer.ui.home.adapter.CharacterActionInterface;
import com.marvel_explorer.ui.home.adapter.CharacterAdapter;
import com.marvel_explorer.ui.home.adapter.CharacterViewModel;
import com.marvel_explorer.ui.search.di.DaggerSearchPresenterComponent;
import com.marvel_explorer.ui.search.SearchContract;
import com.marvel_explorer.ui.search.SearchPresenter;
import com.marvel_explorer.ui.search.di.SearchPresenterModule;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

public class SearchFragment extends Fragment implements SearchContract.View, CharacterActionInterface {

    private View mRootView;
    private SearchView mSearchView;
    private ProgressBar mProgressBar;


    private CharacterAdapter mCharacterAdapter;
    private RecyclerView mRecyclerView;
    private boolean grid_list_flag = true;

    @Inject
    SearchPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        ApplicationComponent appComponent = ((MarvelApplication) getActivity().getApplication()).getAppComponent();

        DaggerSearchPresenterComponent.factory().create(appComponent, new SearchPresenterModule(this)).inject(this);

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRootView = inflater.inflate(R.layout.fragment_search, container, false);

        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mPresenter.attachView(this);

        setupSearchView();
        setupRecyclerView();
        mProgressBar = mRootView.findViewById(R.id.progress_bar);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    private void setupSearchView() {
        mSearchView = mRootView.findViewById(R.id.search_view);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            private Timer timer = new Timer();

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(final String s) {
                if (s.length() == 0) {
                    mPresenter.cancelSubscription();
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    timer.cancel();
                    timer = new Timer();
                    int sleep = 350;
                    if (s.length() == 1)
                        sleep = 5000;
                    else if (s.length() <= 3)
                        sleep = 300;
                    else if (s.length() <= 5)
                        sleep = 200;
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            mPresenter.searchCharacters(s);
                        }
                    }, sleep);
                }
                return true;
            }
        });
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

    public void displayCharacters(List<CharacterViewModel> characterViewModelList) {
        mCharacterAdapter.bindViewModels(characterViewModelList);
    }

    public void onCharacterAddedToFavorites() {}

    public void onCharacterRemovedFromFavorites() {}
}