package com.marvel_explorer.data.services;

import com.marvel_explorer.data.model.MarvelResourceResponse;

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

//    static int FETCH_LIMIT = 100;

    // characters list
    @GET("characters")
    Single<MarvelResourceResponse> listCharacters(
            @Query("nameStartsWith") String keyword,
            @Query("limit") int limit);

    // character by id
    @GET("characters/{characterId}")
    Single<MarvelResourceResponse> getCharacter(@Path("characterId") long characterId);

    // comics list
    @GET("comics")
    Single<MarvelResourceResponse> listComics(
            @Query("nameStartsWith") String keyword,
             @Query("limit") int limit);

    // comic by id
    @GET("comics/{comicId}")
    Single<MarvelResourceResponse> getComic(@Path("comicId") long comicId);

    // creators list
    @GET("creators")
    Single<MarvelResourceResponse> listCreators(
            @Query("nameStartsWith") String keyword,
            @Query("limit") int limit);

    // creator by id
    @GET("creators/{creatorId}")
    Single<MarvelResourceResponse> getCreator(@Path("creatorId") long creatorId);


}
