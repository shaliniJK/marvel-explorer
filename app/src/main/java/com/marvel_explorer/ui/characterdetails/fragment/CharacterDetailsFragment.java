package com.marvel_explorer.ui.characterdetails.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

public class CharacterDetailsFragment extends Fragment implements CharacterDetailsContract.View {

    private View mRootView;

    @Inject
    CharacterDetailsPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        ApplicationComponent appComponent = ((MarvelApplication) getActivity().getApplication()).getAppComponent();

        DaggerCharacterDetailsPresenterComponent.factory().create(appComponent, new CharacterDetailsPresenterModule(this)).inject(this);

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.removeItem(R.id.grid_list_switch);
        menu.findItem(R.id.back_button).setVisible(true);
        menu.findItem(R.id.back_button).setShowAsAction(2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.grid_list_switch:
                return false;
            case R.id.back_button:
                getActivity().getSupportFragmentManager().popBackStack();

                return true;
            default:
                break;
        }
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    public void displayCharacter(Character character) {
        Toast toast = Toast.makeText(getContext(), "hello", Toast.LENGTH_SHORT);
        toast.show();
    }

}
