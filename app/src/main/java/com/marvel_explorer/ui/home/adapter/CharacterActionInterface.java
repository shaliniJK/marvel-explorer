package com.marvel_explorer.ui.home.adapter;

import com.marvel_explorer.data.model.marvelentitytypes.Character;

/**
 *
 * @author koodun
 */
public interface CharacterActionInterface {

    void onFavoriteToggle(String characterId, boolean isFavorite);

    void onItemClicked(String characterId);

}
