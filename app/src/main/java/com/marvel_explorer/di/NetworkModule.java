package com.marvel_explorer.di;

import com.google.gson.Gson;
import com.marvel_explorer.data.services.MarvelRetrofitService;
import com.marvel_explorer.data.services.MarvelService;
import com.marvel_explorer.utils.APIRequestInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author koodun
 */
@Module
public class NetworkModule {

    @Provides
    @Singleton
    public MarvelService provideMarvelService(APIRequestInterceptor interceptor, Gson gson) {
        return MarvelRetrofitService.getMarvelService(interceptor, gson);
    }


    @Provides
    @Singleton
    public APIRequestInterceptor provideAPIRequestInterceptor() {
        return new APIRequestInterceptor();
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        return new Gson();
    }

}
