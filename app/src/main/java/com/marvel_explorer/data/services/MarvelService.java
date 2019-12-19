package com.marvel_explorer.data.services;

import com.marvel_explorer.data.model.MarvelResourceResponse;
import com.marvel_explorer.data.model.marvelentitytypes.Character;
import com.marvel_explorer.data.model.marvelentitytypes.Comic;
import com.marvel_explorer.data.model.marvelentitytypes.Creator;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * A simple interface that lists all the method calls that will made to the Marvel Comics API to retrieve resources
 *
 * @author koodun
 */
public interface MarvelService {

    static int FETCH_LIMIT = 100;

    // all characters list
    @GET("characters")
    Single<MarvelResourceResponse<Character>> listAllCharacters(@Query("limit") int limit);

    // characters list
    @GET("characters")
    Single<MarvelResourceResponse<Character>> listCharacters(
            @Query("nameStartsWith") String keyword,
            @Query("limit") int limit);

    // character by id
    @GET("characters/{characterId}")
    Single<MarvelResourceResponse<Character>> getCharacter(@Path("characterId") String characterId);

    // all comics list
    @GET("comics")
    Single<MarvelResourceResponse<Comic>> listAllComics(@Query("limit") int limit);

    // comics list
    @GET("comics")
    Single<MarvelResourceResponse<Comic>> listComics(
            @Query("nameStartsWith") String keyword,
             @Query("limit") int limit);

    // comic by id
    @GET("comics/{comicId}")
    Single<MarvelResourceResponse<Comic>> getComic(@Path("comicId") String comicId);

    // creators list
    @GET("creators")
    Single<MarvelResourceResponse<Creator>> listCreators(
            @Query("nameStartsWith") String keyword,
            @Query("limit") int limit);

    // creator by id
    @GET("creators/{creatorId}")
    Single<MarvelResourceResponse<Creator>> getCreator(@Path("creatorId") String creatorId);


}
