package com.marvel_explorer.ui.characterdetails.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
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

    private static final String ARG_CHARACTER_ID = "arg_character_id";

    private String characterId;

    @Inject
    CharacterDetailsPresenter mPresenter;

    public static CharacterDetailsFragment newInstance(String character_id) {
        CharacterDetailsFragment fragment = new CharacterDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CHARACTER_ID, character_id);
        fragment.setArguments(args);

        return fragment;
    }

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

        characterId = retrieveCharacterId(savedInstanceState);

        mPresenter.attachView(this);
        mPresenter.fetchCharacter(characterId);
    }

    private String retrieveCharacterId(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return getArguments().getString(ARG_CHARACTER_ID, "");
        } else {
            return savedInstanceState.getString(ARG_CHARACTER_ID, "");
        }
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
        ImageView portraitImageView = mRootView.findViewById(R.id.portrait_imageview);
        TextView nameTextView = mRootView.findViewById(R.id.name_textview);
        TextView descriptionTextView = mRootView.findViewById(R.id.description_textview);

        Glide.with(mRootView)
                .load(character.getThumbnail().getFullImageUrl())
                .fitCenter()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(portraitImageView);

        nameTextView.setText(character.getName());

        if (character.getDescription().isEmpty()) {
            descriptionTextView.setText("No description for now!");
        } else {
            descriptionTextView.setText(character.getDescription());
        }

    }

}
