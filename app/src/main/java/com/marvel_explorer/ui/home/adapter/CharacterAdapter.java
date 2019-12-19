package com.marvel_explorer.ui.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.marvel_explorer.R;

import java.util.ArrayList;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    private List<CharacterViewModel> mCharacterViewModels;
    private CharacterActionInterface mCharacterActionInterface;

    public CharacterAdapter(CharacterActionInterface characterActionInterface) {
        mCharacterViewModels      = new ArrayList<>();
        mCharacterActionInterface = characterActionInterface;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.character_item, parent, false);

        CharacterViewHolder characterViewHolder = new CharacterViewHolder(view, mCharacterActionInterface);

        return characterViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
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
        private CharacterActionInterface mCharacterActionInterface;

        private ImageView thumbnailImageView;
        private TextView nameTextView;
        private ImageButton favoriteButton;

        public CharacterViewHolder(@NonNull View itemView, CharacterActionInterface characterActionInterface) {
            super(itemView);
            this.mView = itemView;
            this.mCharacterActionInterface = characterActionInterface;

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
//                    if (mViewModel.isFavorite()) {
//                        favoriteButton.getDrawable().setAlpha(Color.parseColor("#62727b"));
//                    } else {
//                        favoriteButton.getDrawable().setAlpha(Color.parseColor("#ffffff"));
//                    }
                    mCharacterActionInterface.onFavoriteToggle(mViewModel.getId(), mViewModel.isFavorite());
                }
            });

            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCharacterActionInterface.onItemClicked(mViewModel.getId());
                }
            });
        }
    }
}
