package com.marvel_explorer.data.repository.remote;

import com.google.gson.Gson;
import com.marvel_explorer.data.model.MarvelResourceResponse;
import com.marvel_explorer.data.model.marvelentitytypes.Character;
import com.marvel_explorer.data.model.marvelentitytypes.Comic;
import com.marvel_explorer.data.model.marvelentitytypes.Creator;
import com.marvel_explorer.data.model.marvelentitytypes.MarvelResource;
import com.marvel_explorer.data.services.MarvelService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.functions.Function;

/**
 * A class for getting remote Marvel Resources
 *
 * @author koodun
 */
public class MarvelResourceRemoteDataSource {

    @Inject
    MarvelService mMarvelService;

    @Inject
    Gson mGson;

    @Inject
    public MarvelResourceRemoteDataSource(MarvelService marvelService) {
        mMarvelService = marvelService;
    }

    public Single<List<Character>> getCharactersListResponse(String keyword) {
        return mMarvelService.listCharacters(keyword, MarvelService.FETCH_LIMIT)
                .map(new Function<MarvelResourceResponse, List<Character>>() {
                    @Override
                    public List<Character> apply(MarvelResourceResponse marvelResourceResponse) throws Exception {

                        List<MarvelResource> marvelResources = marvelResourceResponse.getData().getMarvelResources();
                        List<Character> characters = new ArrayList<>();

                        for (MarvelResource marvelResource : marvelResources) {
                            String characterJson = mGson.toJson(marvelResource);

                            Character character = mGson.fromJson(characterJson, Character.class);

                            characters.add(character);
                        }

                        return characters;
                    }
                });

    }

    public Single<Character> getCharacterResponse(long characterId) {
        return null;
//        return mMarvelService.getCharacter(characterId);
    }

    public Single<List<Comic>> getComicsListResponse(String keyword) {
        return null;
    }

    public Single<Comic> getComicResponse(long comicId) {
        return null;
    }

    public Single<List<Creator>> getCreatorsListResponse(String keyword)  {
        return null;
    }

    public Single<Creator> getCreatorResponse(long creatorId) {
        return null;
    }

}
