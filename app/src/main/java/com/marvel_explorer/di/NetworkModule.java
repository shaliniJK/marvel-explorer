package com.marvel_explorer.di;

import com.marvel_explorer.data.repository.remote.MarvelService;
import com.marvel_explorer.utils.APIRequestInterceptor;

import dagger.Module;
import dagger.Provides;

/**
 * @author koodun
 */
@Module
public class NetworkModule {

    @Provides
    public MarvelService provideMarvelRetrofitService(APIRequestInterceptor interceptor) {

        return null;
    }

    @Provides
    public APIRequestInterceptor provideAPIRequestInterceptor() {
        return new APIRequestInterceptor();
    }
}
