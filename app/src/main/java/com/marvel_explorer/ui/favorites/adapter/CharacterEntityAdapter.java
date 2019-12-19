package com.marvel_explorer.ui.favorites.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.marvel_explorer.R;
import com.marvel_explorer.ui.home.adapter.CharacterViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CharacterEntityAdapter extends RecyclerView.Adapter<CharacterEntityAdapter.CharacterViewHolder> {

    private List<CharacterViewModel> mCharacterViewModels;
    private FavoritesActionInterface mFavoritesActionInterface;

    public CharacterEntityAdapter(FavoritesActionInterface favoritesActionInterface) {
        mFavoritesActionInterface = favoritesActionInterface;
        mCharacterViewModels      = new ArrayList<>();
    }

    @NonNull
    @Override
    public CharacterEntityAdapter.CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.character_item, parent, false);

        CharacterEntityAdapter.CharacterViewHolder characterViewHolder = new CharacterEntityAdapter.CharacterViewHolder(view, mFavoritesActionInterface);

        return characterViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterEntityAdapter.CharacterViewHolder holder, int position) {
        holder.bind(mCharacterViewModels.get(position));
    }

    @Override
    public int getItemCount() {
        return mCharacterViewModels.size();
    }

    public void bindViewModels(List<CharacterViewModel> characterViewModelList) {
        mCharacterViewModels.clear();
        mCharacterViewModels.addAll(characterViewModelList);
        notifyDataSetChanged();
    }

    public static class CharacterViewHolder extends RecyclerView.ViewHolder {

        private View mView;
        private CharacterViewModel mViewModel;
        private FavoritesActionInterface mFavoritesActionInterface;

        private ImageView thumbnailImageView;
        private TextView nameTextView;
        private ImageButton favoriteButton;

        public CharacterViewHolder(@NonNull View itemView, FavoritesActionInterface favoritesActionInterface) {
            super(itemView);
            this.mView = itemView;
            this.mFavoritesActionInterface = favoritesActionInterface;

            thumbnailImageView  = itemView.findViewById(R.id.thumbnail_imageview);
            nameTextView        = itemView.findViewById(R.id.name_textview);
            favoriteButton      = itemView.findViewById(R.id.favorite_button);

            setupListeners();
        }

        void bind(CharacterViewModel characterViewModel) {
            this.mViewModel = characterViewModel;

            Glide.with(mView)
                    .load(characterViewModel.getThumbnailUrl())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(thumbnailImageView);

            nameTextView.setText(characterViewModel.getName());
        }

        private void setupListeners() {
            favoriteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mFavoritesActionInterface.onRemoveFavoriteCharacter(mViewModel.getId());
                }
            });

            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mFavoritesActionInterface.onItemClicked(mViewModel.getId());
                }
            });
        }
    }
}
