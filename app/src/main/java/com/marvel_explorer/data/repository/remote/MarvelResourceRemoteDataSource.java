package com.marvel_explorer.data.repository.remote;

import com.google.gson.Gson;
import com.marvel_explorer.data.model.MarvelResourceResponse;
import com.marvel_explorer.data.model.marvelentitytypes.Character;
import com.marvel_explorer.data.model.marvelentitytypes.Comic;
import com.marvel_explorer.data.model.marvelentitytypes.Creator;
import com.marvel_explorer.data.services.MarvelService;

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

    public Single<List<Character>> getAllCharactersListResponse() {
        return mMarvelService.listAllCharacters(MarvelService.FETCH_LIMIT)
                .map(new Function<MarvelResourceResponse, List<Character>>() {
                    @Override
                    public List<Character> apply(MarvelResourceResponse marvelResourceResponse) throws Exception {
                        List<Character> marvelResources = marvelResourceResponse.getData().getMarvelResources();

                        return marvelResources;
                    }
                });
    }

    public Single<List<Character>> getCharactersListResponse(String keyword) {
        return mMarvelService.listCharacters(keyword, MarvelService.FETCH_LIMIT)
                .map(new Function<MarvelResourceResponse, List<Character>>() {
                    @Override
                    public List<Character> apply(MarvelResourceResponse marvelResourceResponse) throws Exception {
                        List<Character> marvelResources = marvelResourceResponse.getData().getMarvelResources();

                        return marvelResources;
                    }
                });
    }

    public Single<Character> getCharacterResponse(String characterId) {
        return mMarvelService.getCharacter(characterId)
                .map(new Function<MarvelResourceResponse<Character>, Character>() {
                    @Override
                    public Character apply(MarvelResourceResponse<Character> characterMarvelResourceResponse)
                            throws Exception {
                        if (characterMarvelResourceResponse.getData().getCount() == 1) {
                            List<Character> marvelResources = characterMarvelResourceResponse.getData()
                                    .getMarvelResources();

                            return marvelResources.get(0);
                        }
                        return null;
                    }
                });
    }

    public Single<List<Comic>> getAllComicsListResponse() {
        return mMarvelService.listAllComics(MarvelService.FETCH_LIMIT)
                .map(new Function<MarvelResourceResponse, List<Comic>>() {
                    @Override
                    public List<Comic> apply(MarvelResourceResponse marvelResourceResponse) throws Exception {
                        List<Comic> marvelResources = marvelResourceResponse.getData().getMarvelResources();

                        return marvelResources;
                    }
                });
    }

    public Single<List<Comic>> getComicsListResponse(String keyword) {
        return null;
    }

    public Single<Comic> getComicResponse(String comicId) {
        return null;
    }

    public Single<List<Creator>> getCreatorsListResponse(String keyword) {
        return null;
    }

    public Single<Creator> getCreatorResponse(String creatorId) {
        return null;
    }

}
