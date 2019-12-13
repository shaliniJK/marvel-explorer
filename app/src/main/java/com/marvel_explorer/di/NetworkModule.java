package com.marvel_explorer.di;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.marvel_explorer.data.model.marvelentitytypes.MarvelResource;
import com.marvel_explorer.data.services.MarvelRetrofitService;
import com.marvel_explorer.data.services.MarvelService;
import com.marvel_explorer.utils.APIRequestInterceptor;
import com.marvel_explorer.utils.JsonDeserializerWithInheritance;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(MarvelResource.class, new JsonDeserializerWithInheritance<MarvelResource>());
        Gson gson = builder.create();

        return gson;
    }

}
